package ch02.ex01;

final class Counter {

	private int count = 0;
	
	synchronized void countUp() {
		count++;
	}
	
	synchronized int get() {
		return count;
	}
}
