/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex07;

import java.util.Comparator;

public final class ComparatorUtility {

	private ComparatorUtility() {
		throw new AssertionError("cannot instanciate");
	}
	
	public static <T extends Comparable<? super T>> Comparator<T> reversedOrderNullsFirst () {
		return (x, y)->{
			Comparator<? super T> c = Comparator.nullsFirst(Comparator.naturalOrder());
			return Math.negateExact(c.compare(x, y));
		};
	}
}
