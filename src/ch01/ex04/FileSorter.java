package ch01.ex04;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public final class FileSorter {

	public static void sortFile (File[] files) {
		Objects.requireNonNull(files, "files must not be null");
		for (final File file: files) {
			Objects.requireNonNull(file, "file must not be null");	
		}
		Arrays.sort(files, (file1, file2)->{
			// 両方ファイルか両方ディレクトリかの検査
			if (file1.isDirectory() == file2.isDirectory()) {
				return file1.compareTo(file2);
			}
			return file1.isDirectory()? -1:1;
		});
	}
}
