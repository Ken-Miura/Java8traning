/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex10;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

public final class TimeCalculationUtility {

	private TimeCalculationUtility () {
		throw new AssertionError("cannot instanciate");
	}
	
	/**
	 * startを起点として、duration時間後における、指定されたタイムゾーン (zoneId) の時刻を算出する。
	 */
	public static ZonedDateTime calculateTimesOfDay(ZonedDateTime start, Duration duration, ZoneId zoneId) {
		Objects.requireNonNull(start, "start must not be null");
		Objects.requireNonNull(duration, "duration must not be null");
		Objects.requireNonNull(zoneId, "zoneId must not be null");
		Instant startInstant = start.toInstant();
		Instant endInstant = startInstant.plus(duration);
		return endInstant.atZone(zoneId);
	}
}
