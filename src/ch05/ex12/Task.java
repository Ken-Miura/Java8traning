/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex12;

import java.time.ZonedDateTime;
import java.util.Objects;

final class Task {

	private final String taskName;
	private final ZonedDateTime taskTime;
	
	Task(String taskName, ZonedDateTime taskTime) {
		Objects.requireNonNull(taskName, "taskName must not be null");
		Objects.requireNonNull(taskTime, "taskTime must not be null");
		this.taskName = taskName;
		this.taskTime = taskTime;
	}

	final String getTaskName() {
		return taskName;
	}

	final ZonedDateTime getTaskTime() {
		return taskTime;
	}
}
