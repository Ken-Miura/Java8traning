package ch01.ex03;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class FileFinder {
	
	public static Set<File> findFile (File directory, String extention) throws IOException {
		Objects.requireNonNull(directory, "directory must not be null");
		if (!directory.isDirectory()) {
			throw new IllegalArgumentException("arg \"directory\" cannot be file");
		}
		Objects.requireNonNull(extention, "extention must not be null");
		
		final String[] foundFileNames = directory.list((dir, fileName)->fileName.endsWith(extention));
		if (foundFileNames == null) {
			throw new IOException("Failed to list up files in " + directory);
		}
		final Set<File> foundFiles = new HashSet<>();
		for (final String fileName: foundFileNames) {
			foundFiles.add(new File(directory + File.separator + fileName));
		}
		return foundFiles;
	}
}
