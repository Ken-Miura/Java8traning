package ch02.ex01;

import java.util.Objects;
import java.util.function.Predicate;

final class CountThread <T> extends Thread {

	private final Counter counter;
	private final T testedObject;
	private final Predicate<T> predicate;
	
	CountThread(Counter counter, T testedObject, Predicate<T> predicate) {
		Objects.requireNonNull(counter, "counter must not be null");
		Objects.requireNonNull(predicate, "predicate must not be null");
		this.counter = counter;
		this.testedObject = testedObject;
		this.predicate = predicate;
	}
	
	@Override
	public void run() {
		if (predicate.test(testedObject)) {
			counter.countUp();
		}
	}
}
