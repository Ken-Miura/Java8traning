package ch01.ex02;


import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class DirectoryFinderTest {

	@Rule
    public final TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Test(expected = NullPointerException.class)
	public void findDirectoryRecursively_nullAsParam_thrownNullPointerException() throws IOException {
		DirectoryFinder.findDirectoryRecursively(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void findDirectoryRecursively_fileAsParam_IllegalArgumentException() throws IOException {
		final File file = tempFolder.newFile();
		DirectoryFinder.findDirectoryRecursively(file);
	}
	
	// TODO
}
