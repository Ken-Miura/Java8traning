/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex12;

import java.time.ZonedDateTime;
import java.util.Objects;

final class Task {

	private final String name;
	private final ZonedDateTime time;
	
	Task(String taskName, ZonedDateTime taskTime) {
		Objects.requireNonNull(taskName, "taskName must not be null");
		Objects.requireNonNull(taskTime, "taskTime must not be null");
		this.name = taskName;
		this.time = taskTime;
	}

	final String getName() {
		return name;
	}

	final ZonedDateTime getTime() {
		return time;
	}
}
