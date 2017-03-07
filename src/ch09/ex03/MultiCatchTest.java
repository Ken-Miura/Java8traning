/**
 * Copyright 2017 Ken Mirua
 */
package ch09.ex03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public final class MultiCatchTest {

	/*
	 * multi-catch節の例外を再スローする際には、メソッドの宣言に対して、multi-catch節の例外の共通の親クラスをスローすると宣言する。
	 */
	@SuppressWarnings("resource") // multi-catch節の例外を再スローする際、メソッドのスロー宣言に何を入れるべきか単に調べるためのものなので無視
	public void process () throws IOException {
		try {
			new FileReader(new File("test"));
			InetAddress.getByName("test");
		} catch (FileNotFoundException | UnknownHostException ex) {
			// logger.log(Level.SEVERE, "...", ex);
			throw ex;
		}
	}
}
