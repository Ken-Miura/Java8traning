/**
 * Copyright 2017 Ken Mirua
 */
package ch06.ex04;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.LongAccumulator;

public final class LongAccumulatorTest {

	private static final int SIZE = 1000;
	
	public static void main (String[] args) {
		List<Long> values = new ArrayList<>();
		Random random = new Random();
		for (int i=0; i<SIZE; i++) {
			values.add(random.nextLong());
		}
		
		// ストリームを使って計算した最大値。LongAccumulatorで算出した値が正しいかの確認用。
		Optional<Long> max = values.stream().max(Long::compare);
		System.out.println("ストリームを使って計算した最大値: " + max.get());
		
		LongAccumulator accumulator1 = new LongAccumulator(Long::max, values.get(0));
		for (final Long l: values) {
			accumulator1.accumulate(l);
		}
		System.out.println("LongAccumulatorを使って計算した最大値: " + accumulator1);
		
		System.out.println();
		
		// ストリームを使って計算した最小値。LongAccumulatorで算出した値が正しいかの確認用。
		Optional<Long> min = values.stream().min(Long::compare);
		System.out.println("ストリームを使って計算した最小値:	 " + min.get());
		
		LongAccumulator accumulator2 = new LongAccumulator(Long::min, values.get(0));
		for (final Long l: values) {
			accumulator2.accumulate(l);
		}
		System.out.println("LongAccumulatorを使って計算した最小値: " + accumulator2);
	}
	
}
