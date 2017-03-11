/**
 * Copyright 2017 Ken Mirua
 */
package ch09.ex08;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

public class PointTest {

	@Test
	public void compareTo_returns0IfPointIsEquelsToTheOther() {
		final Point p1 = new Point(234, 532);
		final Point p2 = new Point(234, 532);
		
		int result = p1.compareTo(p2);
		
		assertThat(result, is(0));
	}

	@Test
	public void compareTo_returnsPositiveIfXIsGreaterThanTheOtherX() {
		final Point p1 = new Point(Integer.MAX_VALUE, 0);
		final Point p2 = new Point(-1, 0);
		
		boolean result = p1.compareTo(p2) > 0; 
		
		assertThat(result, is(true));
	}
	
	@Test
	public void compareTo_returnsNegativeIfXIsLessThanTheOtherX() {
		final Point p1 = new Point(Integer.MIN_VALUE, 0);
		final Point p2 = new Point(1, 0);
		
		boolean result = p1.compareTo(p2) < 0; 
		
		assertThat(result, is(true));
	}
	
	@Test
	public void compareTo_returnsPositiveIfYIsGreaterThanTheOtherY() {
		final Point p1 = new Point(0, Integer.MAX_VALUE);
		final Point p2 = new Point(0, -1);
		
		boolean result = p1.compareTo(p2) > 0; 
		
		assertThat(result, is(true));
	}
	
	@Test
	public void compareTo_returnsNegativeIfYIsLessThanTheOtherY() {
		final Point p1 = new Point(0, Integer.MIN_VALUE);
		final Point p2 = new Point(0, 1);
		
		boolean result = p1.compareTo(p2) < 0; 
		
		assertThat(result, is(true));
	}
}
