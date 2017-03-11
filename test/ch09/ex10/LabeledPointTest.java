/**
 * Copyright 2017 Ken Mirua
 */
package ch09.ex10;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class LabeledPointTest {

	@Test(expected=NullPointerException.class)
	public void compareTo_throwsNullPointerExceptionIfNullIsPassed() {
		new LabeledPoint("test", 0, 10).compareTo(null);
	}
	
	@Test
	public void compareTo_returns0IfEqualsReturnsTrue() {
		LabeledPoint p1 = new LabeledPoint(null, 0, 10);
		LabeledPoint p2 = new LabeledPoint(null, 0, 10);
		
		if (!p1.equals(p2)) {
			throw new AssertionError("must be \"p1.equals(p2)\"");
		}
		
		int result = p1.compareTo(p2);
		
		assertThat(result, is(0));
	}
	
	@Test
	public void compareTo_behavesSymmetric () {
		LabeledPoint p1 = new LabeledPoint(null, 0, 10);
		LabeledPoint p2 = new LabeledPoint(null, 0, 10);
		
		int result1 = p1.compareTo(p2);
		int result2 = p2.compareTo(p1);
		
		assertThat(result1, is(result2));
		
		LabeledPoint p3 = new LabeledPoint("test", -523, 1355);
		LabeledPoint p4 = new LabeledPoint(null, 33, 53);		
		
		int result3 = p3.compareTo(p4);
		int result4 = p4.compareTo(p3);
		
		double sign1 = Math.signum(result3);
		double sign2 = Math.signum(result4);
				
		assertThat(sign1, is( - sign2));
	}
	
	@Test
	public void compareTo_behavesTransitive () {
		LabeledPoint p1 = new LabeledPoint(null, 0, 10);
		LabeledPoint p2 = new LabeledPoint(null, 0, 10);
		LabeledPoint p3 = new LabeledPoint(null, 0, 10);
		
		if (!(p1.equals(p2) && p2.equals(p3))) {
			throw new AssertionError("must be \"p1.equals(p2) && p2.equals(p3)\"");
		}
		assertThat(p1.compareTo(p3), is(0));
		
		LabeledPoint p4 = new LabeledPoint("a", 0, 10);
		LabeledPoint p5 = new LabeledPoint("b", 3, 0);
		LabeledPoint p6 = new LabeledPoint("c", 1, 0);
		
		int result1 = p4.compareTo(p5);
		int result2 = p5.compareTo(p6);
		
		double sign1 = Math.signum(result1);
		double sign2 = Math.signum(result2);
		
		if (sign1 != sign2) {
			throw new AssertionError("must be \"sign1 == sign2\"");
		}
		
		double sign3 = Math.signum(p4.compareTo(p6));
		assertThat(sign3, is(sign1));
		assertThat(sign3, is(sign2));
	}
	
	@Test
	public void compareTo_behavesConsistent () {
		LabeledPoint p1 = new LabeledPoint(null, 0, 10);
		LabeledPoint p2 = new LabeledPoint(null, 0, 10);
		LabeledPoint p3 = new LabeledPoint(null, 5130, 1505);
		
		if (p1.compareTo(p2) != 0) {
			throw new AssertionError("must be \"p1.compareTo(p2) == 0\"");
		}
		
		double sign1 = Math.signum(p1.compareTo(p3));
		double sign2 = Math.signum(p2.compareTo(p3));
		
		assertThat(sign1, is(sign2));
	}
	
	@Test
	public void equals_returnsFalseIfNullIsPassed() {
		LabeledPoint p = new LabeledPoint(null, 0, 10);
		assertThat(p.equals(null), is(false));
	}
	
	@Test
	public void equals_returnsTrueIfObjectItselfIsPassed() {
		LabeledPoint p = new LabeledPoint(null, 0, 10);
		assertThat(p.equals(p), is(true));
	}
	
	@Test
	public void equals_behavesSymmetric() {
		LabeledPoint p1 = new LabeledPoint(null, 0, 10);
		LabeledPoint p2 = new LabeledPoint(null, 0, 10);
		
		boolean result1 = p1.equals(p2);
		boolean result2 = p2.equals(p1);
		
		assertThat(result1, is(result2));
		
		LabeledPoint p3 = new LabeledPoint("test", -523, 1355);
		LabeledPoint p4 = new LabeledPoint(null, 33, 53);		
		
		boolean result3 = p3.equals(p4);
		boolean result4 = p4.equals(p3);
		
		assertThat(result3, is(result4));
	}
	
	@Test
	public void equals_behavesTransitive() {
		LabeledPoint p1 = new LabeledPoint(null, 0, 10);
		LabeledPoint p2 = new LabeledPoint(null, 0, 10);
		LabeledPoint p3 = new LabeledPoint(null, 0, 10);
		
		if (!(p1.equals(p2) && p2.equals(p3))) {
			throw new AssertionError("must be \"p1.equals(p2) && p2.equals(p3)\"");
		}
		assertThat(p1.equals(p3), is(true));
	}
	
	@Test
	public void equals_behavesConsistent() {
		final int trials = 10;
		
		LabeledPoint p1 = new LabeledPoint(null, 0, 10);
		LabeledPoint p2 = new LabeledPoint(null, 0, 10);		
		for (int i=0; i<trials; i++) {
			assertThat(p1.equals(p2), is(true));
		}
		
		LabeledPoint p3 = new LabeledPoint("test", -523, 1355);
		LabeledPoint p4 = new LabeledPoint(null, 33, 53);		
		for (int i=0; i<trials; i++) {
			assertThat(p3.equals(p4), is(false));
		}
	}
	
	@Test
	public void hashCode_returnsSameValueIfBothObjectsHaveSameMember() {
		LabeledPoint p1 = new LabeledPoint(null, 0, 10);
		LabeledPoint p2 = new LabeledPoint(null, 0, 10);
		assertThat(p1.hashCode(), is(p2.hashCode()));
	}
}
