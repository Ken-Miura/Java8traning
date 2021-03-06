package ch02.ex05;

import java.util.stream.Stream;

public final class RandomNumberStreamGenerator {

	public static void main(String...s) {
		//Stream<Long> lcStream = linearCongruentialStream(25214903917L, 11L, 1L << 48, 0L);
		//lcStream.forEach(System.out::println);
		
		Stream<Long> lcStream = linearCongruentialStream(1103515245L, 12345L, 1L << 32, 1L << 20);
		lcStream.limit(100).forEach(System.out::println);
	}
	
	/**
	 * 初期値X0がseedで、 Xn+1=(a*Xn + c)%m　(0からm-1までを一様乱数を生成するアルゴリズム) の値が続く無限Streamを返す。
	 * 返されたStreamは、要素を計算するときにオーバーフローを検出するとjava.lang.ArithmeticExceptionをスローする。
	 * @param a
	 * @param c
	 * @param m
	 * @param seed
	 * @return
	 */
	public static Stream<Long> linearCongruentialStream(long a, long c, long m, long seed) {
		//　線形合同法のパラメータの条件チェック
		if (m<=0) {
			throw new IllegalArgumentException("Illegal parameter m: " + m + ", m must be (m>0)");
		}
		if (a<=0 || a>=m) {
			throw new IllegalArgumentException("Illegal parameter a: " + a + ", a must be (0<a && a<m)");
		}
		if (c<0 || c>=m) {
			throw new IllegalArgumentException("Illegal parameter c: " + c + ", c must be (0<=c && c<m)");
		}
		if (seed<0 || seed>=m) {
			throw new IllegalArgumentException("Illegal parameter seed: " + seed + ", seed must be (0<=seed && seed<m)");
		}
		return Stream.iterate(seed, s->{
			long temp1 = Math.multiplyExact(a, s);
			long temp2 = Math.addExact(temp1, c);
			return temp2%m;
		});
	}
}
