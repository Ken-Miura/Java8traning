package ch02.ex03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public final class StreemSpeedTest {

	private static int CRITERIA = 8;
	
	/**
	 * 
	 * @param args[0] テストに利用するためのファイルのパス
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println("input arg1: file path");
			System.exit(0);
		}
		final String contents = new String(Files.readAllBytes(Paths.get(args[0])), StandardCharsets.UTF_8);
		final List<String> words1 = Arrays.asList(contents.split("[\\P{L}]+")); // \p{L} matches a single code point in the category "letter"
		Stream<String> filteredStream = words1.stream().filter(w->w.length()>CRITERIA);
		
		final long start1 = System.nanoTime();
		final long count1 = filteredStream.count();
		final long result1 = System.nanoTime() - start1;
		System.out.println("non-parallel");
		System.out.println("time: " + result1 + " ns, count: " + count1);
		
		final List<String> words2 = Arrays.asList(contents.split("[\\P{L}]+"));
		Stream<String> filteredParallelStream = words2.parallelStream().filter(w->w.length()>CRITERIA);
		
		final long start2 = System.nanoTime();
		final long count2 = filteredParallelStream.count();
		final long result2 = System.nanoTime() - start2;
		System.out.println("parallel");
		System.out.println("time: " + result2 + " ns, count: " + count2);
		
		System.out.println("difference(non-parallel - parallel): " + (result1-result2) + " ns");
	}

}
