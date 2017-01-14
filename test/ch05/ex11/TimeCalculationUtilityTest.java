/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex11;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeCalculationUtilityTest {

	@Test(expected=NullPointerException.class)
	public void calculateTravelTimeBetween_throwsNullPointerExceptionIfNullIsPassedAsFirstArg() {
		TimeCalculationUtility.calculateTravelTimeBetween(null, ZonedDateTime.now());
	}
	
	@Test(expected=NullPointerException.class)
	public void calculateTravelTimeBetween_throwsNullPointerExceptionIfNullIsPassedAsSecondArg() {
		TimeCalculationUtility.calculateTravelTimeBetween(ZonedDateTime.now(), null);
	}
	
	@Test
	public void calculateTravelTimeBetween_returnsDurationBetweenTwoTimesOfDay() {
		ZonedDateTime timeAtLosAngeles = ZonedDateTime.of(2017, 1, 14, 14, 5, 0, 0, ZoneId.of("CET")); // フランクフルトでの時間
		ZonedDateTime timeAtFrankfurt = ZonedDateTime.of(2017, 1, 14, 16, 40, 0, 0, ZoneId.of("America/Los_Angeles"));
		Duration expected = Duration.ofMinutes(695);
		
		Duration actual = TimeCalculationUtility.calculateTravelTimeBetween(timeAtLosAngeles, timeAtFrankfurt);
		
		assertThat(actual, is(expected));
	}
	
	@Test
	public void calculateTravelTimeBetween_returnsDurationBetweenTwoTimesOfDayWhenSwitchingSummerTimeCase1() {
		ZonedDateTime timeAtLosAngeles = ZonedDateTime.of(2017, 4, 1, 1, 5, 0, 0, ZoneId.of("CET")); // フランクフルトでの時間
		ZonedDateTime timeAtFrankfurt = ZonedDateTime.of(2017, 4, 1, 3, 40, 0, 0, ZoneId.of("America/Los_Angeles"));
		Duration expected = Duration.ofMinutes(695);
		
		Duration actual = TimeCalculationUtility.calculateTravelTimeBetween(timeAtLosAngeles, timeAtFrankfurt);
		
		assertThat(actual, is(expected));
	}
	
	@Test
	public void calculateTravelTimeBetween_returnsDurationBetweenTwoTimesOfDayWhenSwitchingSummerTimeCase2() {
		ZonedDateTime timeAtLosAngeles = ZonedDateTime.of(2016, 10, 30, 1, 5, 0, 0, ZoneId.of("CET")); // フランクフルトでの時間
		ZonedDateTime timeAtFrankfurt = ZonedDateTime.of(2016, 10, 30, 3, 40, 0, 0, ZoneId.of("America/Los_Angeles"));
		Duration expected = Duration.ofMinutes(695);
		
		Duration actual = TimeCalculationUtility.calculateTravelTimeBetween(timeAtLosAngeles, timeAtFrankfurt);
		
		assertThat(actual, is(expected));
	}
	
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
