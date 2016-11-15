package ch02.ex13;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CountShortString {

	private static final int CRITERIA = 12;
	
	public static void main(String[] args) {
		List<String> words = Arrays.asList(args);
		Stream<String> wordsStream = words.parallelStream();
		Map<Integer, Long> stringLengthToNumOfOccurrences = wordsStream
								.filter(s->s.length()<CRITERIA)
								.collect(Collectors.groupingBy(String::length, Collectors.counting()));
		System.out.println(stringLengthToNumOfOccurrences);
	}

}
