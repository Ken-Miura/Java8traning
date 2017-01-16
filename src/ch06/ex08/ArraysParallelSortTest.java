/**
 * Copyright 2017 Ken Mirua
 */
package ch06.ex08;

import java.util.Arrays;
import java.util.Random;

/**
 * screenshot_of_spec.pngのPCで、20回実施しました。
 * その際、配列のサイズが、最小で3から、最大でも17のときには、Arrays.sortよりArrays.parallelSortのほうが早い結果を示しました。
 */
public final class ArraysParallelSortTest {

	private static final Random random = new Random();
	
	public static void main(String[] args) {
		
		for (int arraySize = 1; true; arraySize++) {
			
			int[] intArray = new int[arraySize];
			for (int i=0; i<intArray.length; i++) {
				intArray[i] = random.nextInt();
			}
			
			int[] intArrayForParallelSort = new int[arraySize];
			for (int i=0; i<intArrayForParallelSort.length; i++) {
				intArrayForParallelSort[i] = intArray[i];
			}
			long parallelSortStart = System.nanoTime();
			Arrays.parallelSort(intArrayForParallelSort);
			long parallelSortTime = System.nanoTime() - parallelSortStart;
			
			int[] intArrayForSort = new int[arraySize];
			for (int i=0; i<intArrayForSort.length; i++) {
				intArrayForSort[i] = intArray[i];
			}
			long sortStart = System.nanoTime();
			Arrays.sort(intArrayForSort);
			long sortTime = System.nanoTime() - sortStart;
			
			if (parallelSortTime < sortTime) {
				System.out.println("配列サイズが" + arraySize + "のときArrays.sortより、Arrays.parallelSortのほうが早くなる。");
				System.out.println("Arrays.sort: " + sortTime + " ns");
				System.out.println("Arrays.parallelSort: " + parallelSortTime + " ns");
				break;
			}
		}
	}

}
