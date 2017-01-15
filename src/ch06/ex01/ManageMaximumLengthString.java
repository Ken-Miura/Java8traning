/**
 * Copyright 2017 Ken Mirua
 */
package ch06.ex01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ManageMaximumLengthString {

	public static void main (String[] args) throws IOException, InterruptedException {
		if (args.length != 1) {
			System.out.println("Input text file path");
			System.exit(0);
		}
		
		String contents = new String (Files.readAllBytes(Paths.get(args[0])), StandardCharsets.UTF_8);
		String[] words = contents.split("[\\P{L}]+");
		
		int length = words.length;
		int n = Runtime.getRuntime().availableProcessors();
		ExecutorService service = Executors.newCachedThreadPool();
		final AtomicReference<String> maximumLengthString = new AtomicReference<>("");
		for (int i=0; i<n; i++) {
			int from = i * length / n;
			int to = (i+1) * length / n;
			service.submit(()->{
				for (int j=from; j<to ;j++) {
					maximumLengthString.accumulateAndGet(words[j], (s1, s2)->{ return s1.length()>s2.length()? s1:s2; });
				}
			});
		}
		service.shutdown();
		service.awaitTermination(1, TimeUnit.HOURS);
		
		System.out.println(maximumLengthString);
	}
}
