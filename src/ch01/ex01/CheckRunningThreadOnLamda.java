package ch01.ex01;

import java.util.Arrays;

public class CheckRunningThreadOnLamda {

	public static void main(String[] args) {
		
		String[] sortedArray = new String[2];
		sortedArray[0] = "a";
		sortedArray[1] = "aa";
		
		final String callerThreadName = Thread.currentThread().getName();
		Arrays.sort(sortedArray, (first, second)-> {
			// �����_�����Ŏ��s�����X���b�h���Ăяo�����X���b�h�Ɠ��ꂩ����
			final String calleeThreadName = Thread.currentThread().getName();
			if (callerThreadName.equals(calleeThreadName)) {
				System.out.println("same thread");
			} else {
				System.out.println("different thread");
			}
			return Integer.compare(first.length(), second.length());
		});
	}

}
