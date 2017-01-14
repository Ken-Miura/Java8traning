/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex08;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class ListUTCTimeOffsets {

	public static void main(String[] args) {
		ZoneId.getAvailableZoneIds().stream().forEach(zoneIdString->{
			ZonedDateTime now = ZonedDateTime.now(ZoneId.of(zoneIdString));
			System.out.println("Time zone: " + zoneIdString + ", UTC time offset: " + now.getOffset());
		});
	}

}
