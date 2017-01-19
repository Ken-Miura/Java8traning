/**
 * Copyright 2017 Ken Mirua
 */
package ch06.ex06;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class DisplayWordsFromFiles {

	private static final ConcurrentMap<String, Set<File>> map = new ConcurrentHashMap<>();
	
	/**
	 * 複数ファイルを読み込み、ファイル内にある全ての単語を表示する。
	 * 使用法：読み込みたいファイルのパスを羅列する。
	 * 例. java DisplayWordsFromFiles C:\Users\Ken\alice.txt　C:\Users\Ken\cities.txt C:\Users\Ken\war-and-peace.txt
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		if (args.length < 1) {
			System.out.println("Enter the file path as arg.");
			System.out.println("ex. java DisplayWordsFromFiles C:\\Users\\Ken\\alice.txt");
			System.exit(0);
		}
		List<File> fileList = new ArrayList<>(args.length);
		for (final String path: args) {
			fileList.add(new File(path));
		}
		
		ExecutorService service = Executors.newFixedThreadPool(fileList.size());
		for (final File file: fileList) {
			service.submit(()->{
				try {
					String contents = new String (Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
					String[] words = contents.split("[\\P{L}]+");
					for (final String word: words) {
						// mergeではなくcomputeIfAbsentを利用する利点: キーが存在しない場合にマップするための初期値の計算を、必要な時にだけ実施することで、計算量を削減できる。
						map.computeIfAbsent(word, k->{ Set<File> set = ConcurrentHashMap.newKeySet(); set.add(file); return set; }).add(file);
					}
				} catch (IOException ignored) {
					ignored.printStackTrace();
				}
			});
		}
		service.shutdown();
		service.awaitTermination(1, TimeUnit.HOURS);
		
		System.out.println(map);
	}
}
