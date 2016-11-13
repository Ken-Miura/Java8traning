package ch02.ex06;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class StreamUtility {

	public static Stream<Character> characterStream(String word) {
		return IntStream.range(0, word.length()).boxed().map(word::charAt);
	}
}
