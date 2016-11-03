package ch01.ex03;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.assertEquals;

public class FileFinderTest {

	@Rule
    public final TemporaryFolder tempFolder = new TemporaryFolder();
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void findFile_throwsNullPointerExceptionIfNullIsPassedAsFirstParam() throws IOException {
		exception.expect(NullPointerException.class);
		exception.expectMessage("directory must not be null");
		
		FileFinder.findFile(null, "");
	}
	
	@Test
	public void findFile_throwsIllegalArgumentExceptionIfFileIsPassedAsFirstParam() throws IOException {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("arg \"directory\" cannot be file");
		final File file = tempFolder.newFile();
		
		FileFinder.findFile(file, "");
	}
	
	@Test
	public void findFile_throwsNullPointerExceptionIfNullIsPassedAsSecondParam() throws IOException {
		exception.expect(NullPointerException.class);
		exception.expectMessage("extention must not be null");
		final File directory = tempFolder.newFolder();
		
		FileFinder.findFile(directory, null);
	}
	
	@Test
	public void findFile_returnsAllExsitingFilesIfEmptyStringIsPassedAsSecondParam () throws IOException {
		Set<File> allExistingFiles = new HashSet<>();
		allExistingFiles.add(tempFolder.newFile("1.txt"));
		allExistingFiles.add(tempFolder.newFile("2.jpg"));
		allExistingFiles.add(tempFolder.newFile("3.prn"));
		allExistingFiles.add(tempFolder.newFile("4.docx"));
		allExistingFiles.add(tempFolder.newFile("5.xlsx"));
		allExistingFiles.add(tempFolder.newFile("6.pptx"));
		allExistingFiles.add(tempFolder.newFile("7.csv"));
		allExistingFiles.add(tempFolder.newFile("8.xml"));
		allExistingFiles.add(tempFolder.newFile("tmp"));
		
		Set<File> result = FileFinder.findFile(tempFolder.getRoot(), "");
		
		assertEquals(allExistingFiles, result);
	}
	
	@Test
	public void findFile_returnsSpecifiedExtentionFilesIfExtentionIsPassedAsSecondParam () throws IOException {
		Set<File> specifiedExtentionFiles = new HashSet<>();
		specifiedExtentionFiles.add(tempFolder.newFile("1.txt"));
		tempFolder.newFile("2.jpg");
		tempFolder.newFile("3.prn");
		tempFolder.newFile("4.docx");
		tempFolder.newFile("5.xlsx");
		tempFolder.newFile("6.pptx");
		tempFolder.newFile("7.csv");
		tempFolder.newFile("8.xml");
		tempFolder.newFile("tmp");
		
		Set<File> result = FileFinder.findFile(tempFolder.getRoot(), ".txt");
		
		assertEquals(specifiedExtentionFiles, result);
	}
	
	// for NOT recursively test
	@Test
	public void findFile_returnsFilesInParentDirectoryIfParentDirectoryIsPassedAsFirstParam () throws IOException {
		Set<File> filesInParentDirectory = new HashSet<>();
		filesInParentDirectory.add(tempFolder.newFile("1.txt"));
		tempFolder.newFolder("childDir");
		tempFolder.newFile("childDir" + File.separator + "2.txt");
		
		Set<File> result = FileFinder.findFile(tempFolder.getRoot(), ".txt");
		
		assertEquals(filesInParentDirectory, result);
	}
}
