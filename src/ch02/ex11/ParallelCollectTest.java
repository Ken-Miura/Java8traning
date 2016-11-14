package ch02.ex11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class ParallelCollectTest {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		Stream<String> stream = list.parallelStream();
		
		ArrayList<String> capturedlist = new ArrayList<>(list.size());
		// 結果をマージせず、一つのリストに対して並列に要素を追加する。
		stream.collect(()->capturedlist, (t, u)->{synchronized (t) {t.add(u);}}, (t1, t2)->{/* do nothing */});
		
		System.out.println(capturedlist);
	}

}
