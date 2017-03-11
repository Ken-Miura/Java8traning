/**
 * Copyright 2017 Ken Mirua
 */
package ch09.ex11;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class FindCreditNumByGrep {

	public static void main(String[] args) throws IOException {
		Path homeDir = Paths.get(System.getProperty("user.home"));
		List<String> commands = new ArrayList<>();
		commands.add("grep");
		commands.add("-r");
		commands.add(homeDir.toString());
		ProcessBuilder builder = new ProcessBuilder(commands);
		builder.start();
	}

}
