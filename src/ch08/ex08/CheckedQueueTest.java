/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex08;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public final class CheckedQueueTest {

	@SuppressWarnings("unchecked") // 動的な型チェックのテスト用プログラムなので警告無視
	public static void main(String[] args) {
		try {
			@SuppressWarnings("rawtypes")
			Queue queue = new LinkedList(); // Stringを入れるキューとして利用
			queue = Collections.checkedQueue(queue, String.class);
			
			//...
						
			queue.offer(Integer.valueOf(0)); // CheckedQueueであればこの時点で問題に気づく
			
			//... 
			
			getMoreWork(queue); // CheckedQueueでなければこの時点で問題に気づく
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void getMoreWork(@SuppressWarnings("rawtypes") Queue q) {
		@SuppressWarnings("unused")
		String work = (String) q.poll();
		// 以下workを用いた処理
	}
}
