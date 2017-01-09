/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex01;

import java.time.LocalDate;
import java.time.Period;

public final class CalculateProgrammersDay {

	/**
	 * 入力された年のプログラマの日を計算する。年を入力しない場合は実行した年のプログラマの日を計算する
	 * usage1: java CalculateProgrammersDay 2016 -> 2016-09-12
	 * usage2: java CalculateProgrammersDay -> 2017-09-13 (executing when 2017)
	 */
	public static void main(String[] args) {
		if (args.length!=0 && args.length!=1) {
			System.out.println("Input the year in which you want to knonw programmer's day or input nothing.");
			System.out.println("ex. \"java CalculateProgrammersDay 2016\" or \"java CalculateProgrammersDay\"");
			System.exit(0);
		}
		LocalDate firstDay = null;
		if (args.length==0) {
			firstDay = LocalDate.now().withDayOfMonth(1).withMonth(1);
		} else if (args.length==1) {
			firstDay = LocalDate.of(Integer.parseInt(args[0]), 1, 1);
		} else {
			throw new AssertionError("args.length is not 0 or 1.");
		}
		final LocalDate programmersDay = firstDay.plus(Period.ofDays(255));
		System.out.println(programmersDay);
	}

}
