/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex04;

import java.util.OptionalLong;
import java.util.Random;
import java.util.stream.LongStream;

public final class FindMinimumSeed {

	private static final long a = 11L;
	private static final long v = 246154705703781L;
	private static final long N = 1L << 48;
	private static final long m = 25214903917L;
	private static final long TRIAL_LIMIT = 1000000L;
	
	public static void main(String[] args) {
		OptionalLong minSeed = LongStream.iterate(prev(0), s-> prev(prev(s)))
									.limit(TRIAL_LIMIT)
									.map(s->s^m)
									.min();
		System.out.println("minimum seed: " + minSeed.getAsLong());
		
		Random r = new Random(minSeed.getAsLong());
		for (long i=0; i<TRIAL_LIMIT; i++) {
			if (r.nextDouble() == 0.0) {
				System.out.println("Random.nextDouble returns 0 when trials at " + (i+1));
				break;
			}
		}
	}

	private static long prev(long s) {
		return Math.floorMod((s-a)*v, N);
	}
}
