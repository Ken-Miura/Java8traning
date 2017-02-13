/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex10;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * transientまたはvolatileキーワードを含むファイルをリストアップする。引数として検索対象のルートパスを必要とする。
 * 使用例： java FindTransientOrVolatileFrom C:\Users\Ken\Desktop\src
 */
public final class FindTransientOrVolatileFrom {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println("Enter the path to src.");
			System.exit(0);
		}
		
		try (Stream<Path> pathStream = Files.walk(Paths.get(args[0]))) {
			pathStream.forEach(path->{
				final File entry = path.toFile();
				if (!entry.isFile()) {
					return;
				}
				try (Stream<String> lineStream = Files.lines(path, StandardCharsets.UTF_8)) {
					long count = lineStream.filter(line-> line.contains("transient") || line.contains("volatile")).count();
					if (count > 0) {
						System.out.println(path);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
	}

}
