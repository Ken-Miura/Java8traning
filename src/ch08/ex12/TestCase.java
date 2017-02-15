/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex12;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
@Repeatable(TestCases.class)
public @interface TestCase {
	int params();
	int expected();
}
