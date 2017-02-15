/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class GetContents {

	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.out.println("Enter the URL, username and password for basic auth.");
			System.out.println("ex. java " + GetContents.class.getSimpleName() + " http://leggiero.sakura.ne.jp/xxxxbasic_auth_testxxxx/secret/kaiin_page_top.htm kaiin naisho");
			System.exit(0);
		}
		
		URL url = new URL(args[0]);
		URLConnection connection = url.openConnection();
		Base64.Encoder encoder = Base64.getEncoder();
		String original = args[1] + ":" + args[2];
		String encoded = encoder.encodeToString(original.getBytes(StandardCharsets.UTF_8));
		connection.setRequestProperty("Authorization", "Basic " + encoded);
		connection.connect();
		try (InputStream in = connection.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(inputStreamReader)) {
			String line = null;
			while ((line=reader.readLine()) != null) {
				System.out.println(line);
			}
		}
	}
}
