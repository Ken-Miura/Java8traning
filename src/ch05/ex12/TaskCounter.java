/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex12;

final class TaskCounter {

	private int counter = 0;
	
	void increment () {
		counter++;
	}
	
	void decrement () {
		counter--;
	}
	
	int get () {
		return counter;
	}
}
