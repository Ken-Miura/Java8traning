/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex05;

import org.junit.Test;

public class NumberOfDaysLivingTest {

	@Test(expected=IllegalArgumentException.class)
	public void main_throwsIllegalArgumentExceptionIfMonthIsLessThan1() {
		NumberOfDaysLiving.main(new String[] {"2017", "0", "1"});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void main_throwsIllegalArgumentExceptionIfMonthIsGreaterThan12() {
		NumberOfDaysLiving.main(new String[] {"2017", "13", "1"});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void main_throwsIllegalArgumentExceptionIfDayIsLessThan1() {
		NumberOfDaysLiving.main(new String[] {"2017", "1", "0"});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void main_throwsIllegalArgumentExceptionIfDayIsGreaterThanLastDayOfMonth() {
		NumberOfDaysLiving.main(new String[] {"2017", "1", "32"});
	}
}
