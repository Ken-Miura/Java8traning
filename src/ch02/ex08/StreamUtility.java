package ch02.ex08;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StreamUtility {

	public static void main(String[] args) {
		Stream<Integer> test = zip(Stream.of(1, 2 , 3), Stream.of(4, 5, 6, 7));
		test.forEach(System.out::println);
	}

	// TODO splitatorを利用した実装
	public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
		Objects.requireNonNull(first, "first must not be null");
		Objects.requireNonNull(second, "second must not be null");
		final List<T> list1 = first.collect(Collectors.toList());
		final List<T> list2 = second.collect(Collectors.toList());
		final int minSizeOfLists = Math.min(list1.size(), list2.size());
		
		Stream.Builder<T> builder = Stream.builder();
		for (int i=0; i<minSizeOfLists; i++) {
			builder.add(list1.get(i));
			builder.add(list2.get(i));
		}
		return builder.build();
	}
}
