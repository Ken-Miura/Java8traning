/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex07;

import java.time.LocalDateTime;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class TimeIntervalTest {

	@Test(expected=NullPointerException.class)
	public void TimeInterval_throwsNullPointerExceptionIfNullIsPassedAsFirstArg () {
		new TimeInterval(null, LocalDateTime.now());
	}
	
	@Test(expected=NullPointerException.class)
	public void TimeInterval_throwsNullPointerExceptionIfNullIsPassedAsSecondArg () {
		new TimeInterval(LocalDateTime.now(), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TimeInterval_throwsIllegalArgumentExceptionIfEndIsEarlierThanStrat () {
		new TimeInterval(LocalDateTime.of(2017, 1, 15, 12, 0), LocalDateTime.of(2017, 1, 14, 12, 0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TimeInterval_throwsIllegalArgumentExceptionIfStratIsEqualToEnd () {
		LocalDateTime dateTime = LocalDateTime.now();
		new TimeInterval(dateTime, dateTime);
	}
	
	@Test
	public void overlapWith_returnsFalseIfNullIsPassed() {
		LocalDateTime start = LocalDateTime.of(2017, 1, 14, 12, 0);
		LocalDateTime end = LocalDateTime.of(2017, 1, 15, 12, 0);
		TimeInterval timeInterval = new TimeInterval(start, end);
		
		boolean actual = timeInterval.overlapWith(null);
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void overlapWith_returnsTrueIfOverlappingCompletely() {
		LocalDateTime start = LocalDateTime.of(2017, 1, 14, 12, 0);
		LocalDateTime end = LocalDateTime.of(2017, 1, 15, 12, 0);
		TimeInterval timeInterval = new TimeInterval(start, end);
		
		boolean actual = timeInterval.overlapWith(timeInterval);
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void overlapWith_returnsFalseIfNotOverlapCase1() {
		LocalDateTime start1 = LocalDateTime.of(2017, 1, 14, 12, 0);
		LocalDateTime end1 = LocalDateTime.of(2017, 1, 15, 12, 0);
		TimeInterval timeInterval1 = new TimeInterval(start1, end1);
		
		LocalDateTime start2 = LocalDateTime.of(2017, 1, 16, 12, 0);
		LocalDateTime end2 = LocalDateTime.of(2017, 1, 17, 12, 0);
		TimeInterval timeInterval2 = new TimeInterval(start2, end2);
		
		boolean actual = timeInterval1.overlapWith(timeInterval2);
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void overlapWith_returnsFalseIfNotOverlapCase2() {
		LocalDateTime start1 = LocalDateTime.of(2017, 1, 14, 12, 0);
		LocalDateTime end1 = LocalDateTime.of(2017, 1, 15, 12, 0);
		TimeInterval timeInterval1 = new TimeInterval(start1, end1);
		
		LocalDateTime start2 = LocalDateTime.of(2017, 1, 16, 12, 0);
		LocalDateTime end2 = LocalDateTime.of(2017, 1, 17, 12, 0);
		TimeInterval timeInterval2 = new TimeInterval(start2, end2);
		
		boolean actual = timeInterval2.overlapWith(timeInterval1);
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void overlapWith_returnsTrueIfOverlapCase1() {
		LocalDateTime start1 = LocalDateTime.of(2017, 1, 14, 12, 0);
		LocalDateTime end1 = LocalDateTime.of(2017, 1, 15, 12, 0);
		TimeInterval timeInterval1 = new TimeInterval(start1, end1);
		
		LocalDateTime start2 = LocalDateTime.of(2017, 1, 12, 12, 0);
		LocalDateTime end2 = LocalDateTime.of(2017, 1, 14, 18, 0);
		TimeInterval timeInterval2 = new TimeInterval(start2, end2);
		
		boolean actual = timeInterval1.overlapWith(timeInterval2);
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void overlapWith_returnsFalseIfStartAndEndAreSameCase1() {
		LocalDateTime start1 = LocalDateTime.of(2017, 1, 14, 12, 0);
		LocalDateTime end1 = LocalDateTime.of(2017, 1, 15, 12, 0);
		TimeInterval timeInterval1 = new TimeInterval(start1, end1);
		
		LocalDateTime start2 = LocalDateTime.of(2017, 1, 13, 12, 0);
		LocalDateTime end2 = start1;
		TimeInterval timeInterval2 = new TimeInterval(start2, end2);
		
		boolean actual = timeInterval1.overlapWith(timeInterval2);
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void overlapWith_returnsTrueIfOverlapCase2() {
		LocalDateTime start1 = LocalDateTime.of(2017, 1, 14, 12, 0);
		LocalDateTime end1 = LocalDateTime.of(2017, 1, 15, 12, 0);
		TimeInterval timeInterval1 = new TimeInterval(start1, end1);
		
		LocalDateTime start2 = LocalDateTime.of(2017, 1, 14, 18, 0);
		LocalDateTime end2 = LocalDateTime.of(2017, 1, 16, 12, 0);
		TimeInterval timeInterval2 = new TimeInterval(start2, end2);
		
		boolean actual = timeInterval1.overlapWith(timeInterval2);
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void overlapWith_returnsFalseIfStartAndEndAreSameCase2() {
		LocalDateTime start1 = LocalDateTime.of(2017, 1, 14, 12, 0);
		LocalDateTime end1 = LocalDateTime.of(2017, 1, 15, 12, 0);
		TimeInterval timeInterval1 = new TimeInterval(start1, end1);
		
		LocalDateTime start2 = end1;
		LocalDateTime end2 = LocalDateTime.of(2017, 1, 16, 12, 0);
		TimeInterval timeInterval2 = new TimeInterval(start2, end2);
		
		boolean actual = timeInterval1.overlapWith(timeInterval2);
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void overlapWith_returnsTrueIfOverlapCase3() {
		LocalDateTime start1 = LocalDateTime.of(2017, 1, 14, 12, 0);
		LocalDateTime end1 = LocalDateTime.of(2017, 1, 15, 12, 0);
		TimeInterval timeInterval1 = new TimeInterval(start1, end1);
		
		LocalDateTime start2 = LocalDateTime.of(2017, 1, 14, 18, 0);
		LocalDateTime end2 = LocalDateTime.of(2017, 1, 15, 6, 0);
		TimeInterval timeInterval2 = new TimeInterval(start2, end2);
		
		boolean actual = timeInterval1.overlapWith(timeInterval2);
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void overlapWith_returnsTrueIfOverlapCase4() {
		LocalDateTime start1 = LocalDateTime.of(2017, 1, 14, 12, 0);
		LocalDateTime end1 = LocalDateTime.of(2017, 1, 15, 12, 0);
		TimeInterval timeInterval1 = new TimeInterval(start1, end1);
		
		LocalDateTime start2 = LocalDateTime.of(2016, 12, 1, 0, 0);
		LocalDateTime end2 = LocalDateTime.of(2018, 1, 1, 0, 0);
		TimeInterval timeInterval2 = new TimeInterval(start2, end2);
		
		boolean actual = timeInterval1.overlapWith(timeInterval2);
		
		assertThat(actual, is(true));
	}
}
