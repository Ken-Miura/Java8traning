/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex13;

public final class TestedClass {

	@TestCase(params=4, expected=24)
	@TestCase(params=0, expected=1)
	public static int factorial(int n) {
		return n<=1 ? 1 : n*factorial(n-1);
	}
}
