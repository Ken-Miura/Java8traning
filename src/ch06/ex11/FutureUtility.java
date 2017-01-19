/**
 * Copyright 2017 Ken Mirua
 */
package ch06.ex11;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class FutureUtility {

	public static void main (String[] args) throws IOException, InterruptedException, ExecutionException {
		repeat(FutureUtility::getPasswordAuthentication, FutureUtility::validatePassword);
	}
	
	private static final Scanner scanner = new Scanner (System.in);
	private static PasswordAuthentication getPasswordAuthentication () {
			System.out.print("ユーザ名を入力してください: ");
			final String userName = scanner.nextLine();
			System.out.print("パスワードを入力してください: ");
			final char[] password = scanner.nextLine().toCharArray();
			return new PasswordAuthentication(userName, password);
	}
	
	private static boolean validatePassword (PasswordAuthentication passwordAuthentication) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ignored) {
			ignored.printStackTrace();
		}
		final char[] password = "secret".toCharArray();
		final char[] target = passwordAuthentication.getPassword();
		if (password.length != target.length) {
			return false;
		}
		for (int i=0; i<password.length; i++) {
			if (password[i] != target[i]) {
				return false;	
			}
		}
		System.out.println("正しいパスワードが入力されました。");
		return true;
	}
	
	private FutureUtility () {
		throw new AssertionError("cannot instanciate");
	}
	
	public static <T> CompletableFuture<T> repeat (Supplier<T> action, Predicate<T> until) throws InterruptedException, ExecutionException {
		Objects.requireNonNull(action, "action must not be null");
		Objects.requireNonNull(until, "until must not be null");
		
		final CompletableFuture<T> ret = CompletableFuture.supplyAsync(action);
		if (ret.thenApplyAsync(t->{ return until.test(t); }).get()) {
			return ret;			
		} else {
			return repeat(action, until);
		}
	}
}
