/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex21;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class CollectionUtilityTest {

	@Test(expected=NullPointerException.class)
	public void map_throwsNullPointerExceptionIfNullIsPassedAsFirstParam() {
		CollectionUtility.map(null, (Object o)->o);
	}

	@Test(expected=NullPointerException.class)
	public void map_throwsNullPointerExceptionIfNullIsPassedAsSecondParam() {
		CollectionUtility.map(new Future<Object>() {

			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				return false;
			}

			@Override
			public boolean isCancelled() {
				return false;
			}

			@Override
			public boolean isDone() {
				return false;
			}

			@Override
			public Object get() throws InterruptedException, ExecutionException {
				return null;
			}

			@Override
			public Object get(long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException, TimeoutException {
				return null;
			}
		}, null);
	}
	
	@Test
	public void map_returnsValueMappedFuture() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<String> future = executorService.submit(()->{
			return "Alice";
		});
		
		Future<Character> mappedFuture = CollectionUtility.map(future, s->s.charAt(0));
		assertThat(mappedFuture.get(), is('A'));
	}
}
