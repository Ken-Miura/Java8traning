/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex02;

import java.time.LocalDate;

public final class LeapYearTest {

	public static void main(String[] args) {

		final LocalDate target = LocalDate.of(2000, 2, 29);
		
		// 1年加算 -> 2001-02-28
		System.out.println(target.plusYears(1));
		
		// 4年加算 -> 2004-02-29
		System.out.println(target.plusYears(4));
		
		// 1年を4回加算 -> 2004-02-28
		LocalDate temp1 = target.plusYears(1);
		LocalDate temp2 = temp1.plusYears(1);
		LocalDate temp3 = temp2.plusYears(1);
		LocalDate temp4 = temp3.plusYears(1);
		System.out.println(temp4);
	}
}
