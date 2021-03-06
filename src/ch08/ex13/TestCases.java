/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex13;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(SOURCE)
@Target(METHOD)
public @interface TestCases {
	TestCase[] value();
}
