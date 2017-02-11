/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex02;

/*
 * Math.negateExactが例外をスローする値
 * 引数の型がintのとき: Integer.MIN_VALUE
 * 引数の型がlongのとき: Long.MIN_VALUE
 */
public final class NegateExactTest {

	public static void main(String[] args) {
		try {
			Math.negateExact(Integer.MIN_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Math.negateExact(Long.MIN_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
