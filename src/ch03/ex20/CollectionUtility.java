/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex20;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CollectionUtility {

	public static <T, U> List<U> map (List<T> list, Function<T, U> f) {
		Objects.requireNonNull(list, "list must not be null");
		Objects.requireNonNull(f, "f must not be null");
		return list.stream().map(f::apply).collect(Collectors.toList());
	}
}
