package ch01.ex06;

import org.junit.Test;

public class UtilityTest {

	
	@Test
	public void uncheck_returnsRunnableWhichDoesNothingIfNullIsPassed() {
		final Runnable runnable = Utility.uncheck(null);
		runnable.run();
	}

	@Test(expected=RuntimeException.class)
	public void uncheck_returnsRunnableWhichThrowsRuntimeExceptionIfExceptionThrowingRunnableExIsPassed() {
		final Runnable runnable = Utility.uncheck(()->{throw new Exception();});
		runnable.run();
	}
}
