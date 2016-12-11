/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex23;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public final class Pair<T> implements Map.Entry<T, T>{

	private final T key;
	private T value;
	
	public Pair(T key, T value) {
		Objects.requireNonNull(key, "key must not be null");
		this.key = key;
		this.value = value;
	}
	
	@Override
	public T getKey() {
		return key;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public T setValue(T value) {
		T oldValue = getValue();
		this.value = value;
		return oldValue;
	}
	
	public <U> Pair<U> map (Function<? super T, ? extends U> mapper) {
		Objects.requireNonNull(mapper, "mapper must not be null");
		U mappedKey = mapper.apply(getKey());
		U mappedValue = mapper.apply(getValue());
		return new Pair<U>(mappedKey, mappedValue);
	}
}
