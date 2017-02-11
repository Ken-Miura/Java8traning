/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex01;

public final class UnsignedTest {

	/*
	 * Integer.divideUnsignedとInteger.remainderUnsignedが必要な理由
	 * /, %の記号での計算処理では、期待する計算結果にならないため。
	 */
	public static void main(String[] args) {
		// 加算
		System.out.print("符号なしintとしての加算結果:　");
		System.out.println(Integer.toUnsignedLong(Integer.MAX_VALUE + Integer.MAX_VALUE));
		System.out.print("期待する加算結果:　");
		System.out.println(((1L<<31)-1) + ((1L<<31)-1));
		
		// 減算
		System.out.print("符号なしintとしての減算結果:　");
		System.out.println(Integer.toUnsignedLong(Integer.MIN_VALUE - Integer.MAX_VALUE));
		System.out.print("期待する減算結果:　");
		System.out.println((1L<<31) - ((1L<<31)-1));
		
		// 除算
		System.out.print("符号なしintとしての除算結果:　");
		System.out.println(Integer.toUnsignedLong(Integer.divideUnsigned(Integer.MIN_VALUE, 2)));
		System.out.print("期待する除算結果:　");
		System.out.println((1L<<31)/2);
		
		// 比較
		System.out.print("符号なしintとしての比較結果:　");
		System.out.println(Integer.compareUnsigned(Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out.print("期待する比較結果:　");
		System.out.println(Long.compare((1L<<31), ((1L<<31)-1)));
	}
}
