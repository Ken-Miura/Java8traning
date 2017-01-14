/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex07;

import java.time.LocalDateTime;
import java.util.Objects;

public final class TimeInterval {

	private final LocalDateTime start;
	private final LocalDateTime end;
	
	public TimeInterval (LocalDateTime start, LocalDateTime end) {
		Objects.requireNonNull(start, "start must not be null");
		Objects.requireNonNull(start, "end must not be null");
		if (start.isEqual(end) || start.isAfter(end)) {
			throw new IllegalArgumentException("");
		}
		this.start = start;
		this.end = end;
	}
	
	public boolean overlapWith (TimeInterval timeInterval) {
		if (timeInterval == null) {
			return false;
		}
		
		if (start.isAfter(timeInterval.getStart()) && (start.isBefore(timeInterval.getEnd()) || start.isEqual(timeInterval.getEnd())) ) {
			return true;
		}
		
		if ((end.isEqual(timeInterval.getStart()) || end.isAfter(timeInterval.getStart())) && end.isBefore(timeInterval.getEnd())) {
			return true;
		}
		
		// timeIntervalがthisに包含されているケース
		if ((start.isBefore(timeInterval.getStart()) || start.isEqual(timeInterval.getStart()))
				&& (end.isEqual(timeInterval.getEnd()) || end.isAfter(timeInterval.getEnd()))) {
			return true;
		}
		
		// thisがtimeIntervalに包含されているケース
		if ((timeInterval.getStart().isBefore(start) || timeInterval.getStart().isEqual(start))
				&& (timeInterval.getEnd().isEqual(end) || timeInterval.getEnd().isAfter(end))) {
			return true;
		}
		return false;
	}

	public final LocalDateTime getStart() {
		return start;
	}

	public final LocalDateTime getEnd() {
		return end;
	}
}
