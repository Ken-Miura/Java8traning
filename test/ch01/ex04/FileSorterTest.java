package ch01.ex04;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.assertArrayEquals;

public class FileSorterTest {

	@Rule
    public final TemporaryFolder tempFolder = new TemporaryFolder();
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void sortFile_throwsNullPointerExceptionIfNullIsPassed() {
		exception.expect(NullPointerException.class);
		exception.expectMessage("files must not be null");
		
		FileSorter.sortFile(null);
	}
	
	@Test
	public void sortFile_throwsNullPointerExceptionIfFilesIncludingNullIsPassed() throws IOException {
		exception.expect(NullPointerException.class);
		exception.expectMessage("file must not be null");
		final File[] files = new File[2];
		files[0] = tempFolder.newFile();
		files[1] = null;
		
		FileSorter.sortFile(files);
	}

	@Test
	public void sortFile_sortsByNameIfFilesAerPassed () throws IOException {
		final File file1 = tempFolder.newFile("a.txt");
		final File file2 = tempFolder.newFile("b.txt");
		final File file3 = tempFolder.newFile("c.txt");
		final File[] filesSortedByName = new File[3];
		filesSortedByName[0] = file1;
		filesSortedByName[1] = file2;
		filesSortedByName[2] = file3;
		
		final File[] testedFiles = new File[3];
		testedFiles[0] = file2;
		testedFiles[1] = file3;
		testedFiles[2] = file1;
		
		FileSorter.sortFile(testedFiles);
		
		assertArrayEquals(filesSortedByName, testedFiles);
	}
	
	@Test
	public void sortFile_sortsByNameIfDirectoriesArePassed () throws IOException {
		final File directory1 = tempFolder.newFolder("a");
		final File directory2 = tempFolder.newFolder("b");
		final File directory3 = tempFolder.newFolder("c");
		final File[] directoriesSortedByName = new File[3];
		directoriesSortedByName[0] = directory1;
		directoriesSortedByName[1] = directory2;
		directoriesSortedByName[2] = directory3;
		
		final File[] testedDirectories = new File[3];
		testedDirectories[0] = directory2;
		testedDirectories[1] = directory3;
		testedDirectories[2] = directory1;
		
		FileSorter.sortFile(testedDirectories);
		
		assertArrayEquals(directoriesSortedByName, testedDirectories);
	}
	
	@Test
	public void sortFile_sortsByTypeIfDirectoryAndFileArePassed () throws IOException {
		final File directory = tempFolder.newFolder("b");
		final File file = tempFolder.newFile("a.txt");
		final File[] directoryAndFileSortedByType = new File[2];
		directoryAndFileSortedByType[0] = directory;
		directoryAndFileSortedByType[1] = file;
		
		final File[] testedDirectoryAndFile = new File[2];
		testedDirectoryAndFile[0] = file;
		testedDirectoryAndFile[1] = directory;
		
		FileSorter.sortFile(testedDirectoryAndFile);
		
		assertArrayEquals(directoryAndFileSortedByType, testedDirectoryAndFile);
	}
	
	@Test
	public void sortFile_sortsByTypeAndNameIfDirectoriesAndFilesArePassed () throws IOException {
		final File directory1 = tempFolder.newFolder("b");
		final File directory2 = tempFolder.newFolder("d");
		final File file1 = tempFolder.newFile("a.txt");
		final File file2 = tempFolder.newFile("c.txt");
		final File[] directoriesAndFilesSortedByTypeAndName = new File[4];
		directoriesAndFilesSortedByTypeAndName[0] = directory1;
		directoriesAndFilesSortedByTypeAndName[1] = directory2;
		directoriesAndFilesSortedByTypeAndName[2] = file1;
		directoriesAndFilesSortedByTypeAndName[3] = file2;
		
		final File[] testedDirectoriesAndFiles = new File[4];
		testedDirectoriesAndFiles[0] = file2;
		testedDirectoriesAndFiles[1] = directory1;
		testedDirectoriesAndFiles[2] = file1;
		testedDirectoriesAndFiles[3] = directory2;
		
		FileSorter.sortFile(testedDirectoriesAndFiles);
		
		assertArrayEquals(directoriesAndFilesSortedByTypeAndName, testedDirectoriesAndFiles);
	}
	
}
