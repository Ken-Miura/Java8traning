/**
 * Copyright 2017 Ken Mirua
 */
package ch09.ex07;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class GetContentsToFile {

	public static void main(String[] args) {
		URL url = null;
		try {
			url = new URL("https://www.google.co.jp/");
			try (InputStream in = url.openStream();) {
				Files.copy(in, Paths.get("output.html"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
