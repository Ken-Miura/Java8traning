/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex06;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class EnumerateAllFridayThe13thOfThe20thCentury {

	public static void main(String[] args) {
		LocalDate firstDayOfThe20thCentury = LocalDate.of(1901, 1, 1);
		LocalDate lastDayOfThe20thCentury = LocalDate.of(2000, 12, 31);
		
		LocalDate d = firstDayOfThe20thCentury.withDayOfMonth(13);
		while (d.isBefore(lastDayOfThe20thCentury) || d.isEqual(lastDayOfThe20thCentury)) {
			if (d.getDayOfWeek() == DayOfWeek.FRIDAY) {
				System.out.println(d);
			}
			d = d.plusMonths(1);
		}
	}
}
