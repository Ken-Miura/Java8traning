/**
 * Copyright 2016 Ken Mirua
 */
package ch04.ex05;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public final class BindingsUtility {

	private BindingsUtility () {
		throw new AssertionError("cannot instanciate");
	}
	
	public static <T, R> ObservableValue<? extends R> observe(Function<? super T, ? extends R> f, ObservableValue<T> t) {
		Objects.requireNonNull(f, "f must not be null");
		Objects.requireNonNull(t, "t must not be null");
		
		final ObjectProperty<R> objectProperty = new SimpleObjectProperty<>();
		objectProperty.bind(Bindings.createObjectBinding(new Callable<R>() {

			@Override
			public R call() throws Exception {
				return f.apply(t.getValue());
			}
		}, t));
		return objectProperty;
	}
	
	public static <T, U, R> ObservableValue<R> observe(BiFunction<? super T, ? super U, R> f, ObservableValue<T> t, ObservableValue<U> u) {
		Objects.requireNonNull(f, "f must not be null");
		Objects.requireNonNull(t, "t must not be null");
		Objects.requireNonNull(u, "u must not be null");
		
		final ObjectProperty<R> objectProperty = new SimpleObjectProperty<>();
		objectProperty.bind(Bindings.createObjectBinding(new Callable<R>() {

			@Override
			public R call() throws Exception {
				return f.apply(t.getValue(), u.getValue());
			}
		}, t, u));
		return objectProperty;
	}
}
