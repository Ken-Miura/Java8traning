/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex10;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeCalculationUtilityTest {

	@Test(expected=NullPointerException.class)
	public void calculateTimesOfDay_throwsNullPointerExceptionIfNullIsPassedAsFirstArg() {
		TimeCalculationUtility.calculateTimesOfDay(null, Duration.ofHours(1), ZoneId.systemDefault());
	}
	
	@Test(expected=NullPointerException.class)
	public void calculateTimesOfDay_throwsNullPointerExceptionIfNullIsPassedAsSecondArg() {
		TimeCalculationUtility.calculateTimesOfDay(ZonedDateTime.now(), null, ZoneId.systemDefault());
	}

	@Test(expected=NullPointerException.class)
	public void calculateTimesOfDay_throwsNullPointerExceptionIfNullIsPassedAsThirdArg() {
		TimeCalculationUtility.calculateTimesOfDay(ZonedDateTime.now(), Duration.ofHours(1), null);
	}
	
	@Test
	public void calculateTimesOfDay_returnsTimesOfDayAtSpecifiedTimeZoneAfterDuration() {
		ZonedDateTime timeAtLosAngeles = ZonedDateTime.of(2017, 1, 14, 3, 5, 0, 0, ZoneId.of("America/Los_Angeles"));
		ZonedDateTime expected = ZonedDateTime.of(2017, 1, 14, 22, 55, 0, 0, ZoneId.of("CET")); // フランクフルトでの時間
		
		ZonedDateTime actual = TimeCalculationUtility.calculateTimesOfDay(timeAtLosAngeles, Duration.ofMinutes(650), ZoneId.of("CET"));
		
		assertThat(actual, is(expected));
	}
	
	@Test
	public void calculateTimesOfDay_returnsTimesOfDayAtSpecifiedTimeZoneAfterDurationWhenSwithingSummerTimeCase1() {
		ZonedDateTime timeAtLosAngeles = ZonedDateTime.of(2017, 4, 1, 1, 5, 0, 0, ZoneId.of("America/Los_Angeles"));
		ZonedDateTime expected = ZonedDateTime.of(2017, 4, 1, 20, 55, 0, 0, ZoneId.of("CET")); // フランクフルトでの時間
		
		ZonedDateTime actual = TimeCalculationUtility.calculateTimesOfDay(timeAtLosAngeles, Duration.ofMinutes(650), ZoneId.of("CET"));
		
		assertThat(actual, is(expected));
	}
	
	@Test
	public void calculateTimesOfDay_returnsTimesOfDayAtSpecifiedTimeZoneAfterDurationWhenSwithingSummerTimeCase2() {
		ZonedDateTime timeAtLosAngeles = ZonedDateTime.of(2016, 10, 30, 1, 5, 0, 0, ZoneId.of("America/Los_Angeles"));
		ZonedDateTime expected = ZonedDateTime.of(2016, 10, 30, 19, 55, 0, 0, ZoneId.of("CET")); // フランクフルトでの時間
		
		ZonedDateTime actual = TimeCalculationUtility.calculateTimesOfDay(timeAtLosAngeles, Duration.ofMinutes(650), ZoneId.of("CET"));
		
		assertThat(actual, is(expected));
	}
}
