package ch01.ex02;


import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class DirectoryFinderTest {

	@Rule
    public final TemporaryFolder tempFolder = new TemporaryFolder();
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void findDirectoryRecursively_throwsNullPointerExceptionIfNullIsPassed() throws IOException {
		exception.expect(NullPointerException.class);
		exception.expectMessage("directory must not be null");
		DirectoryFinder.findDirectoryRecursively(null);
	}
	
	@Test
	public void findDirectoryRecursively_throwsIllegalArgumentExceptionIfFileIsPassed() throws IOException {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("arg \"directory\" cannot be file");
		final File file = tempFolder.newFile();
		
		DirectoryFinder.findDirectoryRecursively(file);
	}
	
	@Test
	public void findDirectoryRecursively_returnsEmptySetIfEmptyDirectoyIsPassed() throws IOException {
		final Set<File> emptySet = new HashSet<>();
		final File emptyDirectory = tempFolder.newFolder();
		
		final Set<File> result = DirectoryFinder.findDirectoryRecursively(emptyDirectory);
		
		assertThat(result, is(emptySet));
	}
	
	@Test
	public void findDirectoryRecursively_returnsSetIncludingOneDirectoryIfOneDirectoryIsPassed() throws IOException {
		final Set<File> setIncludingOneDirectory = new HashSet<>();
		final File directory = tempFolder.newFolder("level1");
		setIncludingOneDirectory.add(tempFolder.newFolder("level1", "level2"));
		
		final Set<File> result = DirectoryFinder.findDirectoryRecursively(directory);
		
		assertThat(result, is(setIncludingOneDirectory));
	}
	
	@Test
	public void findDirectoryRecursively_returnsSetIncludingOneDirectoryIfOneDirectoryAndOneFileArePassed() throws IOException {
		final Set<File> setIncludingOneDirectory = new HashSet<>();
		final File directory = tempFolder.newFolder("root");
		setIncludingOneDirectory.add(tempFolder.newFolder("root", "level1"));
		tempFolder.newFile("root" + File.separator + "tmp");
		
		final Set<File> result = DirectoryFinder.findDirectoryRecursively(directory);
		
		assertThat(result, is(setIncludingOneDirectory));
	}
	
	@Test
	public void findDirectoryRecursively_returnsSetIncludingTwoDirectoriesIfTwoHierarichicalDirectoriesArePassed() throws IOException {
		final Set<File> setIncludingTwoDirectories = new HashSet<>();
		final File directory = tempFolder.newFolder("root");
		setIncludingTwoDirectories.add(tempFolder.newFolder("root", "level1"));
		setIncludingTwoDirectories.add(tempFolder.newFolder("root", "level1", "level2"));
		
		final Set<File> result = DirectoryFinder.findDirectoryRecursively(directory);
		
		assertThat(result, is(setIncludingTwoDirectories));
	}
	
	@Test
	public void findDirectoryRecursively_returnsSetIncludingThreeDirectoriesIfThreeHierarichicalDirectoriesArePassed() throws IOException {
		final Set<File> setIncludingThreeDirectories = new HashSet<>();
		final File directory = tempFolder.newFolder("root");
		setIncludingThreeDirectories.add(tempFolder.newFolder("root", "level1"));
		setIncludingThreeDirectories.add(tempFolder.newFolder("root", "level1", "level2"));
		setIncludingThreeDirectories.add(tempFolder.newFolder("root", "level1", "level2", "level3"));
		
		final Set<File> result = DirectoryFinder.findDirectoryRecursively(directory);
		
		assertThat(result, is(setIncludingThreeDirectories));
	}

}
