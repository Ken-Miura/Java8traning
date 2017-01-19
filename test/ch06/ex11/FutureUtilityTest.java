/**
 * Copyright 2017 Ken Mirua
 */
package ch06.ex11;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class FutureUtilityTest {

	@Test(expected=NullPointerException.class)
	public void repeat_throwsNullPointerExceptionIfNullIsPassedAsFirstArg() throws InterruptedException, ExecutionException{
		FutureUtility.repeat(null, s->true);
	}

	@Test(expected=NullPointerException.class)
	public void repeat_throwsNullPointerExceptionIfNullIsPassedAsSecondArg() throws InterruptedException, ExecutionException{
		FutureUtility.repeat(()->{return "test";}, null);
	}
}
