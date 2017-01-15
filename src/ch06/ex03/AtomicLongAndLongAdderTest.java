/**
 * Copyright 2017 Ken Mirua
 */
package ch06.ex03;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public final class AtomicLongAndLongAdderTest {
	
	private static final AtomicLong atomicLongCounter = new AtomicLong();
	private static final LongAdder longAdderCounter = new LongAdder();
	
	private static final int NUM_OF_THREADS = 1000;
	private static final int NUM_OF_TRIAL_PER_THREAD = 100000;
	
	private static ExecutorService service = null;
	
	public static void main(String[] args) throws InterruptedException {
		service = Executors.newFixedThreadPool(NUM_OF_THREADS);

		calculateAtomicLongPerformance();
		System.out.println();
		calculateLongAdderPerformance();
		
		service.shutdown();
		service.awaitTermination(1, TimeUnit.HOURS);
	}
	
	static void calculateAtomicLongPerformance() throws InterruptedException {
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch eachThreadLatch = new CountDownLatch(NUM_OF_THREADS);
		for (int i=0; i<NUM_OF_THREADS; i++) {
			service.submit(()->{
				try {
					startLatch.await();
				} catch (InterruptedException igonored) {
					igonored.printStackTrace();
				}
				for (int j=0; j<NUM_OF_TRIAL_PER_THREAD; j++) {
					atomicLongCounter.incrementAndGet();
				}
				eachThreadLatch.countDown();
			});
		}
		startLatch.countDown();
		long start = System.nanoTime();
		eachThreadLatch.await();
		long time = System.nanoTime() - start;
		
		System.out.println("AtomicLong");
		System.out.println("counter: " + atomicLongCounter);
		System.out.println("calculation time: " + time + " ns");
	}
	
	static void calculateLongAdderPerformance() throws InterruptedException {
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch eachThreadLatch = new CountDownLatch(NUM_OF_THREADS);
		for (int i=0; i<NUM_OF_THREADS; i++) {
			service.submit(()->{
				try {
					startLatch.await();
				} catch (InterruptedException igonored) {
					igonored.printStackTrace();
				}
				for (int j=0; j<NUM_OF_TRIAL_PER_THREAD; j++) {
					longAdderCounter.increment();
				}
				eachThreadLatch.countDown();
			});
		}
		startLatch.countDown();
		long start = System.nanoTime();
		eachThreadLatch.await();
		long time = System.nanoTime() - start;

		System.out.println("LongAdder");
		System.out.println("counter: " + longAdderCounter);
		System.out.println("calculation time: " + time + " ns");
	}

}
