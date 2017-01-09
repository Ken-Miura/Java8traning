/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex03;

import java.time.LocalDate;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static ch05.ex03.TimeUtility.next;

public class TimeUtilityTest {

	@Test(expected=NullPointerException.class)
	public void next_throwsNullPointerExceptionIfNullIsPassed() {
		next(null);
	}

	@Test
	public void next_returnsTemporalAdjusterWhichReturnsNextDayThatConditionIsSatisfied() {
		final Predicate<LocalDate> condition = w-> w.getDayOfWeek().getValue() < 6; // 平日->true, 土日->false
		final LocalDate friday = LocalDate.of(2017, 1, 13);
		final LocalDate saturday = LocalDate.of(2017, 1, 14);
		final LocalDate sunday = LocalDate.of(2017, 1, 15);
		final LocalDate monday = LocalDate.of(2017, 1, 16);
		final LocalDate tuesday = LocalDate.of(2017, 1, 17);
		
		final LocalDate actual1 = friday.with(next(condition));
		final LocalDate actual2 = saturday.with(next(condition));
		final LocalDate actual3 = sunday.with(next(condition));
		final LocalDate actual4 = monday.with(next(condition));
		
		assertThat(actual1, is(monday));
		assertThat(actual2, is(monday));
		assertThat(actual3, is(monday));
		assertThat(actual4, is(tuesday));
	}
}
