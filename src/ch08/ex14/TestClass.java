/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex14;

import java.util.Objects;

/*
 * Objects.requireNonNullの使用方法：下記のクラスのようにコンストラクタやメソッドのパラメータの引数チェックに利用する。
 * もっと役立つエラーメッセージとなる方法???
 */
public final class TestClass {

	@SuppressWarnings("unused")
	private final Object testMember;
	
	TestClass(Object testMember) {
		this.testMember = Objects.requireNonNull(testMember, "testMember must not be null");
	}

	static void testMethod(Object o) {
		Objects.requireNonNull(o, "o must not be null");
	}
	
	public static void main (String[] args) {
		
		try {
			new TestClass(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			TestClass.testMethod(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
