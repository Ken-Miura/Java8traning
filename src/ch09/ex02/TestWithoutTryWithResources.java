/**
 * Copyright 2017 Ken Mirua
 */
package ch09.ex02;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;

public final class TestWithoutTryWithResources {

	public static void main(String[] args) {
		Scanner scanner = null;
		PrintWriter printWriter = null;
		try {
			scanner = new Scanner(new StringReader("Scanner and PrintWriter test"));
			StringWriter out = new StringWriter();
			printWriter = new PrintWriter(out);
			while(scanner.hasNext()) {
				String s = scanner.next();
				printWriter.println(s);
			}
			printWriter.flush();
			System.out.println(out.toString());
		} catch (Exception e) {
			if (scanner!=null) {
				try {
					scanner.close();
				} catch (Exception surppressedException) {
					e.addSuppressed(surppressedException);
				}
			}
			if (printWriter!=null) {
				try {
					printWriter.close();
				} catch (Exception surppressedException) {
					e.addSuppressed(surppressedException);
				}
			}
			throw e;
		} finally {
			if (scanner!=null) {
				try {
					scanner.close();
				} catch (Exception surppressedException) {
					surppressedException.printStackTrace();
				}
			}
			if (printWriter!=null) {
				try {
					printWriter.close();
				} catch (Exception surppressedException) {
					surppressedException.printStackTrace();
				}
			}
		}
	}
}
