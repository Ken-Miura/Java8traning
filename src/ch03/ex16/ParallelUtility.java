/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex16;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public final class ParallelUtility {

	/*
	 * 有効なユースケース：？
	 * 第三のパラメータが必要かどうか：有効な有効なユースケースが思いつかないため、必要かどうか判断できない。
	 */
	public static <T> void doInOrderAcync (Supplier<T> first, BiConsumer<T, Throwable> second) {
		Objects.requireNonNull(first, "first must not be null");
		Thread t = new Thread () {
			@Override
			public void run() {
				try {
					T result = first.get();
					second.accept(result, null);
				} catch (Throwable t) {
					second.accept(null, t);
				}
			}
		};
		t.start();
	}
}
