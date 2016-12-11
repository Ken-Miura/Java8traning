/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex18;

import java.util.Objects;
import java.util.function.Function;

public final class LamdaExpressionUtility {

	/*
	 * Functionが必要なメソッドにuncheckedを通して、例外処理なしに渡せること（コンパイルエラーにならないこと）を確認
	 */
	public static void main (String[] args) {
		System.out.println(testMethod2(unchecked(LamdaExpressionUtility::testMethod1), "test"));
	}
	
	private static String testMethod2(Function<? super String, ? extends String> function, String arg) {
		return function.apply(arg);
	}

	private static String testMethod1 (String s) throws Exception {
		return s;
	}
	
	private LamdaExpressionUtility() {
		throw new AssertionError("cannot instanciate");
	}
	
	public static <T, R> Function<? super T, ? extends R> unchecked (ExceptionThrowableFunction<? super T, ? extends R> exceptionThrowableFunction) {
		Objects.requireNonNull(exceptionThrowableFunction, "exceptionThrowableFunction must not be null");
		return t->{
			try {
				return exceptionThrowableFunction.apply(t);	
			} catch (Exception e) {
				throw new RuntimeException(e);
			} catch (Throwable th) {
				throw th;
			}
		};
	}
	
	@FunctionalInterface
	public static interface ExceptionThrowableFunction<T, R> {
		public R apply(T t) throws Exception;
	}
}
