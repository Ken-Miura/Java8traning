package ch02.ex12;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class CountShortString {

	private static final int CRITERIA = 12;
	
	public static void main(String[] args) {
		AtomicInteger[] counters = new AtomicInteger[CRITERIA]; // 基準より長さが短い文字列が出現した回数を、長さ毎に記録するカウンタ
		for (int i=0; i<counters.length; i++) {
			counters[i] = new AtomicInteger();
		}
		
		List<String> words = Arrays.asList(args);
		words.parallelStream().forEach(s->{ if (s.length() < CRITERIA) { counters[s.length()].getAndIncrement(); } });
		
		System.out.println(Arrays.toString(counters));
	}

}
