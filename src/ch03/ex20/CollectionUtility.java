/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex20;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public final class CollectionUtility {

	public static <T, U> List<U> map (List<T> list, Function<T, U> f) {
		Objects.requireNonNull(list, "list must not be null");
		Objects.requireNonNull(f, "f must not be null");
		
		List<U> mappedList = new ArrayList<>(list.size());
		for (final T t: list) {
			mappedList.add(f.apply(t));
		}
		return mappedList;
	}
}
