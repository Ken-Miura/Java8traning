/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex08;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

public final class ListUTCTimeOffsets {

	public static void main(String[] args) {
		final Stream<String> zoneIdsStream = ZoneId.getAvailableZoneIds().stream();
		zoneIdsStream.forEach(zoneIdString->{
			ZonedDateTime now = ZonedDateTime.now(ZoneId.of(zoneIdString));
			System.out.println("Time zone: " + zoneIdString + ", UTC time offset: " + now.getOffset());
		});
	}

}
