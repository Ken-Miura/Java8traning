package ch02.ex10;

import java.util.Objects;
import java.util.stream.Stream;

public final class StreamUtility {

	public static void main(String[] args) {
		System.out.println(calculateAverage(Stream.of(0.0, 1.0, 2.0)));
	}

	// 単純にreduceで合計して、countで割れないのはreduceもcountも終端操作で一度しかストリームに適用できないから
	public static double calculateAverage(Stream<Double> stream) {
		Objects.requireNonNull(stream, "stream must not be null");
		
		Averager helper = stream.reduce(new Averager(), 
				(Averager averager, Double d)->{ averager.sum+=d; averager.count++; return averager;}, 
				(averager1, averager2)->{return new Averager(averager1.sum+averager2.sum, averager1.count+averager2.count);});
		return helper.count<=0? 0: helper.sum/helper.count;
	}
	
	private static class Averager {
		double sum;
		long count;
		
		Averager() {
			this.sum = 0.0;
			this.count = 0L;
		}
		
		Averager(double sum, long count) {
			this.sum = sum;
			this.count = count;
		}
	}
}
