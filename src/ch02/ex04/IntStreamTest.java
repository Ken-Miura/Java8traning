package ch02.ex04;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class IntStreamTest {

	public static void main(String[] args) {
		int[] values = {1, 4, 9, 16};
		
		// Stream.of(values)は、Stream<int[]>となる
		Stream<int[]> intArrayStream = Stream.of(values);
		intArrayStream.forEach(System.out::println);
		
		IntStream intStream = Arrays.stream(values);
		intStream.forEach(System.out::println);
	}

}
