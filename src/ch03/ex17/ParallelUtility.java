/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex17;

import java.util.Objects;
import java.util.function.Consumer;

public final class ParallelUtility {

	private ParallelUtility () {
		throw new AssertionError("cannot instanciate");
	}
	
	/**
	 * handler内の処理に入ることを目視確認
	 */
	public static void main (String[] args) {
		doInOrderAcync(()->{ throw new RuntimeException("first"); }, ()->{}, t->{
			System.out.println("--start handler--");
			System.out.println(t.toString());
			System.out.println("--end handler--");
		});
		
		doInOrderAcync(()->{}, ()->{ throw new RuntimeException("second"); }, t->{
			System.out.println("--start handler--");
			System.out.println(t.toString());
			System.out.println("--end handler--");
		});

		doInOrderAcync(()->{ throw new RuntimeException("first"); }, ()->{ throw new RuntimeException("second"); }, t->{
			System.out.println("--start handler--");
			System.out.println(t.toString());
			System.out.println("--end handler--");
		});
	}
	
	public static <T> void doInOrderAcync (Runnable first, Runnable second, Consumer<Throwable> handler) {
		Objects.requireNonNull(first, "first must not be null");
		Objects.requireNonNull(second, "second must not be null");
		Objects.requireNonNull(handler, "handler must not be null");
		Thread t1 = new Thread () {
			@Override
			public void run() {
				try {
					first.run();
				} catch (Throwable t) {
					synchronized (handler) {
						handler.accept(t);	
					}
				}
			}
		};
		Thread t2 = new Thread () {
			@Override
			public void run() {
				try {
					second.run();
				} catch (Throwable t) {
					synchronized (handler) {
						handler.accept(t);	
					}
				}
			}
		};
		t1.start();
		t2.start();
	}
}
