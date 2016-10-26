package ch01.ex02;


import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.*;

public class DirectoryFinderTest {

	@Rule
    public final TemporaryFolder tempFolder = new TemporaryFolder();
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void findDirectoryRecursively_nullAsParam_thrownNullPointerException() throws IOException {
		exception.expect(NullPointerException.class);
		exception.expectMessage("directory must not be null");
		DirectoryFinder.findDirectoryRecursively(null);
	}
	
	@Test
	public void findDirectoryRecursively_fileAsParam_IllegalArgumentException() throws IOException {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("arg \"directory\" cannot be file");
		final File file = tempFolder.newFile();
		DirectoryFinder.findDirectoryRecursively(file);
	}
	
	@Test
	public void findDirectoryRecursively_emptyDirectoryAsParam_emptySet() throws IOException {
		final Set<File> expected = new HashSet<>();
		final File emptyDirectory = tempFolder.newFolder();
		final Set<File> actual = DirectoryFinder.findDirectoryRecursively(emptyDirectory);
		assertEquals(expected, actual);
	}
	
	@Test
	public void findDirectoryRecursively_directoryWhichHasOneDirectoryAsParam_SetWhichHasOneDirectory() throws IOException {
		final Set<File> expected = new HashSet<>();
		final File directory = tempFolder.newFolder("level1");
		expected.add(tempFolder.newFolder("level1", "level2"));
		final Set<File> actual = DirectoryFinder.findDirectoryRecursively(directory);
		assertEquals(expected, actual);
	}
	
	@Test
	public void findDirectoryRecursively_directoryWhichHasOneDirectoryAndOneFileAsParam_SetWhichHasOneDirectory() throws IOException {
		final Set<File> expected = new HashSet<>();
		final File directory = tempFolder.newFolder("level1");
		expected.add(tempFolder.newFolder("level1", "level2"));
		tempFolder.newFile("level1" + File.separator + "tmp");
		final Set<File> actual = DirectoryFinder.findDirectoryRecursively(directory);
		assertEquals(expected, actual);
	}
	
	@Test
	public void findDirectoryRecursively_directoryWhichHasTwoDirectoriesAsParam_SetWhichHasTwoDirectories() throws IOException {
		final Set<File> expected = new HashSet<>();
		final File directory = tempFolder.newFolder("level1");
		expected.add(tempFolder.newFolder("level1", "level2"));
		expected.add(tempFolder.newFolder("level1", "level2", "level3"));
		final Set<File> actual = DirectoryFinder.findDirectoryRecursively(directory);
		assertEquals(expected, actual);
	}

}
