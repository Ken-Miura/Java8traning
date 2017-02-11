/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class CountLongWordWithStream {
	
	private static final int CRITERIA = 12;

	public static void main(String[] args) throws IOException {
		if (args.length!=1) {
			System.out.println("Enter the path to text file.");
			System.exit(0);
		}
		
		long start = System.nanoTime();
		String contents = new String(Files.readAllBytes(Paths.get(args[0])), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		long count = words.stream().filter(word->word.length()>CRITERIA).count();
		long time = System.nanoTime() - start;
		System.out.println("long word count: " + count + ", elapsed time: " + time + " ns");
	}

}
