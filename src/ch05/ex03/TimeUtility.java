/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex03;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;
import java.util.function.Predicate;

public final class TimeUtility {

	public static void main (String... args) {		
		LocalDate friday = LocalDate.of(2017, 1, 13);
		System.out.println(friday.with(next(w-> w.getDayOfWeek().getValue() < 6)));
		
		LocalDate saturday = LocalDate.of(2017, 1, 14);
		System.out.println(saturday.with(next(w-> w.getDayOfWeek().getValue() < 6)));
		
		LocalDate sunday = LocalDate.of(2017, 1, 15);
		System.out.println(sunday.with(next(w-> w.getDayOfWeek().getValue() < 6)));
		
		LocalDate monday = LocalDate.of(2017, 1, 16);
		System.out.println(monday.with(next(w-> w.getDayOfWeek().getValue() < 6)));
	}
	
	public static TemporalAdjuster next (Predicate<LocalDate> condition) {
		Objects.requireNonNull(condition, "condition must not be null");
		return TemporalAdjusters.ofDateAdjuster(localDate->{ 
			LocalDate result = localDate;
			do {
				result = result.plusDays(1);
			} while (!condition.test(result));
			return result;
		});
	}
}
