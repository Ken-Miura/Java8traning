package ch01.ex11;

public interface I {
	//void f();
	//default void f() { System.out.println("I"); };
	static void f() { System.out.println("static I"); };
}
