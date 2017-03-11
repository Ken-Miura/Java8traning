/**
 * Copyright 2017 Ken Mirua
 */
package ch09.ex11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FindCreditNumByGrep {

	private static final String CREDIT_NUM_PATTERN = "[0-9]\\{4\\}-[0-9]\\{4\\}-[0-9]\\{4\\}-[0-9]\\{4\\}";
	private static final String CREDIT_NUM_PATTERN_IN_JAVA = "[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}";
	private static final Pattern pattern = Pattern.compile(CREDIT_NUM_PATTERN_IN_JAVA);

	public static void main(String[] args) throws IOException, InterruptedException {
		Path homeDir = Paths.get(System.getProperty("user.home"));
		ProcessBuilder builder = new ProcessBuilder("grep", "-r", CREDIT_NUM_PATTERN, homeDir.toString());
		Process process = builder.start();
		int result = process.waitFor();
		if (result != 0) {
			System.out.println("grep failed");
			System.exit(result);
		}
		
		List<String> creditNums = new ArrayList<>();
		try (InputStream in = process.getInputStream();
				InputStreamReader isReader = new InputStreamReader(in, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(isReader);) {
			String line = null;
			while ((line=reader.readLine())!=null) {
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					creditNums.add(matcher.group());
				}
			}
		}
		System.out.println("credit numbers");
		creditNums.stream().forEach(System.out::println);
	}

}
