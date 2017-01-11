/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class NumberOfDaysLiving {

	/**
	 * 今までに生きてきた日数を表示する。
	 * @param 誕生年月日
	 * 
	 * 使用例: java NumberOfDaysLiving 1989 11 2
	 */
	public static void main(String[] args) {
		if (args.length!=3) {
			System.out.println("Input year, month and day you were born.");
			System.out.println("ex. \"java NumberOfDaysLiving 1989 11 2\"");
			System.exit(0);
		}
		final int year = Integer.parseInt(args[0]);
		final int month = Integer.parseInt(args[1]);
		final int day = Integer.parseInt(args[2]);
		if (month<1 || month>12) {
			throw new IllegalArgumentException("number of month must be 1 or more and 12 or less");
		}
		if (day<1 || day>LocalDate.of(year, month, 1).lengthOfMonth()) {
			throw new IllegalArgumentException("number of day must be 1 or more and "+ LocalDate.of(year, month, 1).lengthOfMonth() +" or less");
		}
		
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(year, month, day);
		if (today.isEqual(birthday) || today.isAfter(birthday)) {
			System.out.println("You are alive for " + ChronoUnit.DAYS.between(birthday, today) + " days total.");
		} else {
			System.out.println("You have not been born yet.");
		}
	}

}
