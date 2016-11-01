package ch01.ex06;

// Callable<Void>ではいけない理由:
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
			{ System.out.println("Zzz"); Thread.sleep(100); })).
		start();
	}
}
