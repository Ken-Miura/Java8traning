/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex09;

import java.util.Scanner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class ScannerUtilityTest {

	@Test(expected=NullPointerException.class)
	public void words_throwsNullPointerExceptionIfNullIsPassed() {
		ScannerUtility.words(null);
	}
	
	@Test
	public void words_returnWordsStreamFromScanner() {
		try (Scanner scanner = new Scanner("a b c word test\ndef");) {
			assertThat(ScannerUtility.words(scanner).count(), is(6L));
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void lines_throwsNullPointerExceptionIfNullIsPassed() {
		ScannerUtility.lines(null);
	}
	
	@Test
	public void lines_returnLinesStreamFromScanner() {
		try (Scanner scanner = new Scanner("a b c word test\ndef");) {
			assertThat(ScannerUtility.lines(scanner).count(), is(2L));
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void integers_throwsNullPointerExceptionIfNullIsPassed() {
		ScannerUtility.integers(null);
	}
	
	@Test
	public void integers_returnIntegersStreamFromScanner() {
		try (Scanner scanner = new Scanner("1 2 3\n4.0 5\n");) {
			assertThat(ScannerUtility.integers(scanner).count(), is(3L));
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void doubles_throwsNullPointerExceptionIfNullIsPassed() {
		ScannerUtility.doubles(null);
	}

	@Test
	public void doubles_returnDoublesStreamFromScanner() {
		try (Scanner scanner = new Scanner("1 2.0 3.0\n4 5\n");) {
			assertThat(ScannerUtility.doubles(scanner).count(), is(5L));
		}
	}
}
