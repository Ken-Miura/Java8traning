/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex09;

import java.lang.reflect.Field;
import java.util.Comparator;

public final class ComparatorUtility {

	private ComparatorUtility () {
		throw new AssertionError("cannot instanciate");
	}
	
	public static <T> Comparator<T> lexicographicComparator (String... fieldNames) {
		return (obj1, obj2)->{
			for (final String fieldName: fieldNames) {
				try {
					Field f1 = obj1.getClass().getDeclaredField(fieldName);
					f1.setAccessible(true);
					@SuppressWarnings("unchecked")
					Comparable<? super Comparable<?>> c1 = (Comparable<? super Comparable<?>>) f1.get(obj1);
					
					Field f2 = obj2.getClass().getDeclaredField(fieldName);
					f2.setAccessible(true);
					Comparable<?> c2 = (Comparable<?>) f2.get(obj2);
					
					int result = c1.compareTo(c2);
					if (result != 0) {
						return result;
					}
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
			return 0;
		};
	}
}
