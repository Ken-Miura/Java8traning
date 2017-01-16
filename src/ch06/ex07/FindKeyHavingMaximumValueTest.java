/**
 * Copyright 2017 Ken Mirua
 */
package ch06.ex07;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class FindKeyHavingMaximumValueTest {

	/**
	 * alice.txtを読み込み、単語に分割した後、最も長い文字列の単語を表示する。alice.txt内の最も長い文字列は、"unenforceability"（ch06.ex01の結果より）
	 * @throws URISyntaxException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, URISyntaxException {		
		URL url = FindKeyHavingMaximumValueTest.class.getResource("alice.txt");
		String contents = new String(Files.readAllBytes(new File(url.toURI()).toPath()), StandardCharsets.UTF_8);
		String[] words = contents.split("[\\P{L}]+");
		
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>(words.length);
		for (final String word: words) {
			map.put(word, Long.valueOf(word.length()));
		}
		
		Map.Entry<String, Long> entry = map.reduceEntries(1, (e1, e2)->{ return e1.getValue().longValue()>e2.getValue().longValue()? e1:e2; });
		System.out.println(entry);
	}
}
