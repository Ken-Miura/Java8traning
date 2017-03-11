/**
 * Copyright 2017 Ken Mirua
 */
package ch09.ex06;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public final class WriteLinesByReverseOrder {

	public static void main(String[] args) throws URISyntaxException, IOException {
		URL url = WriteLinesByReverseOrder.class.getResource("test.txt");
		Path path = Paths.get(url.toURI());
		List<String> lines = Files.readAllLines(path);
		Collections.reverse(lines);
		Files.write(path.resolveSibling("output.txt"), lines);
	}

}
