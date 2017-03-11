/**
 * Copyright 2017 Ken Mirua
 */
package ch09.ex09;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class LabeledPointTest {

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
