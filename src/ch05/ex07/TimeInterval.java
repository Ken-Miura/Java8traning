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
	
	/**
	 * タイムインターバルが重なっているかどうかを返す。
	 * 例えば、下記の二つのインターバル（年月日は同一として省略。時分秒のみ記載）の場合はそれぞれ以下の結果となる。
	 * 10:00:00-12:00:00と13:00:00-14:00:00 → false
	 * 10:00:00-12:00:00と12:00:00-14:00:00 → false
	 * 10:00:00-12:00:00と11:00:00-13:00:00 → true
	 */
	public boolean overlapWith (TimeInterval timeInterval) {
		if (timeInterval == null) {
			return false;
		}
		
		if (start.isAfter(timeInterval.getStart()) && start.isBefore(timeInterval.getEnd()) ) {
			return true;
		}
		
		if (end.isAfter(timeInterval.getStart()) && end.isBefore(timeInterval.getEnd())) {
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
