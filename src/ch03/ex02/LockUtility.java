package ch03.ex02;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public final class LockUtility {

	private LockUtility () {
		throw new AssertionError("cannot instanciate");
	}
	
	public static void withLock (ReentrantLock lock, Runnable runnable) {
		Objects.requireNonNull(lock, "lock must not be null");
		lock.lock();
		try {
			if (runnable!=null) {
				runnable.run();
			}
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final ReentrantLock lock = new ReentrantLock();
		withLock(lock, ()->{System.out.println("withLock test");});
	}
}
