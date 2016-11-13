package ch02.ex07;

import java.util.stream.Stream;

public final class StreamUtility {

	// isFiniteを作成するのはよくない考え。
	// ストリームの要素が有限かどうかを判断するために終端操作が必要になる。そして終端操作の後のストリームは利用できない。
	// なのでisFiniteで有限かどうか調べた後のストリームは使うことができなくなる。そして使えないストリームに関しての情報を取得できても、その情報の利用シーンはない。
	public static void main(String...strings) {
		Stream<Double> infiniteStream = Stream.generate(Math::random);
		System.out.println(isFinite(infiniteStream));
		
		Stream<Integer> finiteStream = Stream.of(1, 2, 3);
		System.out.println(isFinite(finiteStream));
	}
	
	public static <T> boolean isFinite(Stream<T> stream) {
		long result = stream.spliterator().getExactSizeIfKnown();
		return result!=-1;
	}
}
