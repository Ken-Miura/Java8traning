/**
 * Copyright 2017 Ken Mirua
 */
package ch09.ex05;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class WriteByReverseOrder {

	public static void main(String[] args) throws IOException, URISyntaxException {
		URL url = WriteByReverseOrder.class.getResource("test.txt");
		Path path = Paths.get(url.toURI());
		String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
		String output = new StringBuilder(content).reverse().toString();
		Files.write(path.resolveSibling(Paths.get("output.txt")), output.getBytes(StandardCharsets.UTF_8));
	}

}
