/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex03;

/*
 * %, floorMod, 自作のremメソッドのどれが負の値に対して最も簡単か？ → 余りを求める際に負の値にならないようにするのは、floorModが最も簡単。
 */
public final class Algorithm {

	private Algorithm () {
		throw new AssertionError("cannot instanciate");
	}
	
	public static int euclideanAlgorithmUsingPercent (int a, int b) {
		if (b==0) {
			return a<0? Math.negateExact(a): a;
		}
		return euclideanAlgorithmUsingPercent(b, a%b);
	}
	
	public static int euclideanAlgorithmUsingFloorMod (int a, int b) {
		if (b==0) {
			return a<0? Math.negateExact(a): a;
		}
		return euclideanAlgorithmUsingFloorMod(b, Math.floorMod(a, b));
	}
	
	public static int euclideanAlgorithmUsingRem (int a, int b) {
		if (b==0) {
			return a<0? Math.negateExact(a): a;
		}
		return euclideanAlgorithmUsingRem(b, rem(a, b));
	}
	
	private static int rem(int a, int b) {
		final int remainder = a%b;
		return remainder<0 ? Math.negateExact(remainder): remainder;
	}

}
