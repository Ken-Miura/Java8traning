/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class Grep {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Enter the path to file and regular expression.");
			System.out.println("ex. java Grep C:\\workspace_Java8traning\\Java8traning\\src\\ch08\\ex15\\cities.txt Houston");
			System.exit(0);
		}
		
		try (Stream<String> lines = Files.lines(Paths.get(args[0]))) {
			lines.filter(Pattern.compile(args[1]).asPredicate())
					.forEach(System.out::println);
		}
	}

}
