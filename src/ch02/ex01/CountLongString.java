package ch02.ex01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CountLongString {

	private static final int CRITERIA = 12;
	
	public static void main(String[] args) {
		final List<String> words = Arrays.asList(args);
		final Counter counter = new Counter();
		final List<CountThread<String>> countThreads = new ArrayList<>(words.size());
		for (final String word: words) {
			countThreads.add(new CountThread<String>(counter, word, s->s.length() > CRITERIA));
		}
		for (final CountThread<String> countThread: countThreads) {
			countThread.start();
		}
		System.out.println(counter.get());
	}

}
