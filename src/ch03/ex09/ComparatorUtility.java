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
					Comparable<? super Comparable<?>> c1 = null;
					try {
						f1.setAccessible(true);
						@SuppressWarnings("unchecked")
						Comparable<? super Comparable<?>> temp = (Comparable<? super Comparable<?>>) f1.get(obj1);
						c1 = temp;
					} catch (IllegalArgumentException | IllegalAccessException e) {
						throw new RuntimeException(e);	
					}
					
					Field f2 = obj2.getClass().getDeclaredField(fieldName);
					Comparable<?> c2 = null;
					try {
						f2.setAccessible(true);
						c2 = (Comparable<?>) f2.get(obj2);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						throw new RuntimeException(e);	
					}
					
					int result = c1.compareTo(c2);
					if (result != 0) {
						return result;
					}
				} catch (NoSuchFieldException | SecurityException e) {
					throw new RuntimeException(e);
				}
			}
			return 0;
		};
	}
}
