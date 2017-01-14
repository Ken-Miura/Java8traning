/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex09;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class ListTimeZonesWhereUTCOffsetIsLessThan1H {

	public static void main(String[] args) {
		ZoneId.getAvailableZoneIds().stream()
							.filter(zoneIdString->Math.abs(ZonedDateTime.now(ZoneId.of(zoneIdString)).getOffset().getTotalSeconds()) < 3600)
							.forEach(zoneIdString->{System.out.println("Time zone: " + zoneIdString);});
	}

}
