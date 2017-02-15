/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex12;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class TestedClass {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> testedClass = Class.forName("ch08.ex12.TestedClass");
		Method testedMethod = testedClass.getMethod("factorial", int.class);
		TestCase[] testCases = testedMethod.getAnnotationsByType(TestCase.class);
		for (final TestCase testCase: testCases) {
			Object result = testedMethod.invoke(null, testCase.params());
			if (!result.equals(testCase.expected())){
				throw new AssertionError(testCase.expected() + " is expected but actual is " + result);
			}
		}
		System.out.println("All the tests passed.");
	}

	@TestCase(params=4, expected=24)
	@TestCase(params=0, expected=1)
	public static int factorial(int n) {
		return n==0 ? 1 : n*factorial(n-1);
	}
}
