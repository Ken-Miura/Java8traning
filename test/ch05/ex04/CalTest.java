/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex04;

import org.junit.Test;

public class CalTest {

	@Test(expected=IllegalArgumentException.class)
	public void main_throwsIllegalArgumentExceptionIfMonthIsLessThan1() {
		Cal.main(new String[] {"0", "2017"});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void main_throwsIllegalArgumentExceptionIfMonthIsGreaterThan12() {
		Cal.main(new String[] {"13", "2017"});
	}
}
