/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class ExtractCityStateZipCode {

	private static final String PATTERN_STRING = "(?<city>[\\p{L} ]+),\\s*(?<state>[A-Z]{2}),\\s*(?<zipCode>[\\d]{5}|[\\d]{9})";
	private static final Pattern pattern = Pattern.compile(PATTERN_STRING);
	
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println("Enter the path to file.");
			System.exit(0);
		}
		try (Stream<String> lines = Files.lines(Paths.get(args[0]))) {
			lines.forEach(line->{
				Matcher matcher = pattern.matcher(line);
				if (matcher.matches()) {
					System.out.print("City: " + matcher.group("city") + ", ");
					System.out.print("State: " + matcher.group("state") + ", ");
					System.out.println("Zip code: " + matcher.group("zipCode"));
				}
			});
		}
	}

}
