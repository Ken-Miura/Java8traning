package ch01.ex06;

// RunnableExの代わりにCallable<Void>ではいけない理由:　Callable<Void>だとcallの返り値の型としてVoidが指定されているので、return null;のような構文が最後に必要になるため
public final class Utility {

	public static Runnable uncheck (RunnableEx runner) {
		return ()->{
			try {
				if (runner != null)
					runner.run();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}
	
	public static void main (String[] args) {
		new Thread(uncheck(()->
			{ System.out.println("Zzz"); Thread.sleep(100);})).
		start();
	}
}
