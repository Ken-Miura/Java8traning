package ch01.ex06;

// RunnableEx�̑����Callable<Void>�ł͂����Ȃ����R:�@Callable<Void>����call�̕Ԃ�l�̌^�Ƃ���Void���w�肳��Ă���̂ŁAreturn null;�̂悤�ȍ\�����Ō�ɕK�v�ɂȂ邽��
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
