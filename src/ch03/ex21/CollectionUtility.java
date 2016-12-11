/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex21;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public final class CollectionUtility {
	
	public static <T, U> Future<U> map (Future<T> future, Function<T, U> f) {
		Objects.requireNonNull(future, "future must not be null");
		Objects.requireNonNull(f, "f must not be null");
		
		return new Future<U>() {

			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				return future.cancel(mayInterruptIfRunning);
			}

			@Override
			public boolean isCancelled() {
				return future.isCancelled();
			}

			@Override
			public boolean isDone() {
				return future.isDone();
			}

			@Override
			public U get() throws InterruptedException, ExecutionException {
				return f.apply(future.get());
			}

			@Override
			public U get(long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException, TimeoutException {
				return f.apply(future.get(timeout, unit));
			}
		};
	}
}
