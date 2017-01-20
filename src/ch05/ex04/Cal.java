/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex04;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class Cal {

	/**
	 * 指定した年月のカレンダーを表示する。
	 * 使用例： java Cal 3 2013
	 */
	public static void main(String[] args) {
		if (args.length!=2) {
			System.out.println("Input month and year you want to see.");
			System.out.println("ex. \"java Cal 3 2013\"");
			System.exit(0);
		}

		final int month = Integer.parseInt(args[0]);
		final int year = Integer.parseInt(args[1]);
		if (month<1 || month>12) {
			throw new IllegalArgumentException("number of month must be 1 or more and 12 or less");
		}
		
		LocalDate firstDay = LocalDate.of(year, month, 1);
		final int space = firstDay.getDayOfWeek().getValue()-1;
		for (int i=0; i<space; i++) {
			System.out.print("   ");
		}
		int lengthOfMonth = firstDay.lengthOfMonth();
		for (int i=0; i<lengthOfMonth; i++) {
			LocalDate d = firstDay.plusDays(i);
			String formatted = String.format("%2d ", d.getDayOfMonth());
			if (d.getDayOfWeek() == DayOfWeek.SUNDAY) {
				formatted = formatted.substring(0, formatted.length()-1).concat("\n");
			}
			System.out.print(formatted);
		}
		System.out.println();
	}
}
