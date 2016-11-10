package ch02.ex01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// 単一のカウンタを更新するためにスレッドを使いたくないのは、カウンタに対して排他制御を注意深く実装しなければならなくなるから
public final class CountLongString {

	private static final int CRITERIA = 12;
	
	public static void main(String[] args) throws InterruptedException {
		final List<String> words = Arrays.asList(args);
		System.out.println(numOfLongString(words));
	}

	public static int numOfLongString(List<String> words) throws InterruptedException {
		Objects.requireNonNull(words, "words must not be null");
		final List<CountThread<String>> countThreads = new ArrayList<>(words.size());
		final Counter counter = new Counter();
		for (final String word: words) {
			countThreads.add(new CountThread<String>(counter, word, s->s.length() > CRITERIA));
		}
		for (final CountThread<String> countThread: countThreads) {
			countThread.start();
		}
		for (final CountThread<String> countThread: countThreads) {
			countThread.join();
		}
		return counter.get();
	}
}
