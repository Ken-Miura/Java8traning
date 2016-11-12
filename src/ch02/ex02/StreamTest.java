package ch02.ex02;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StreamTest {

	private static int MAX_NUM = 5;
	private static int CRITERIA = 3;
	
	public static void main(String[] args) {
		//Set<String> words = new HashSet<>();
		List<String> words = new ArrayList<>();
		words.add("1111");
		words.add("2222");
		words.add("3333");
		words.add("4444");
		words.add("5555");
		words.add("6666");
		words.add("7777");
		words.forEach(System.out::println);
		System.out.println("--before filtering and limiting--");
		
		Stream<String> wordStream = words.stream();
		Set<String> extractedWords = wordStream
				.filter(w->{System.out.println("call filter for " + w); return w.length()>CRITERIA;})
				.limit(MAX_NUM)
				.collect(Collectors.toSet());
		
		System.out.println("--after filtering and limiting--");
		extractedWords.forEach(System.out::println);
	}

}
