package ch01.ex02;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class DirectoryFinder {
	
	public static Set<File> findDirectoryRecursively (File directory) throws IOException {
		Objects.requireNonNull(directory, "directory must not be null");
		if (!directory.isDirectory()) {
			throw new IllegalArgumentException("arg \"directory\" cannot be file");
		}
		
		// ラムダ式版
		// File[] subDirectories = directory.listFiles((file)->file.isDirectory());
		// メソッド参照版
		File[] subDirectories = directory.listFiles(File::isDirectory);
		
		if (subDirectories == null) {
			throw new IOException("Failed to list up directories in " + directory);
		}
		final Set<File> foundDirectories =  new HashSet<>();
		for (final File subDirectory: subDirectories) {
			foundDirectories.add(subDirectory);
			foundDirectories.addAll(findDirectoryRecursively(subDirectory));
		}
		return foundDirectories;
	}
}
