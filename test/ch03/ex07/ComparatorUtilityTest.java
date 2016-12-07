/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex07;

import java.util.Arrays;

import org.junit.Test;
import ch03.ex07.ComparatorUtility.Order;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class ComparatorUtilityTest {

	@Test
	public void comparatorGenerator_returnsNormalOrderComparatorIfNormalIsPassed() {
		String[] strings = {"c", "b", "a"};
		Arrays.sort(strings, ComparatorUtility.comparatorGenerator(Order.NORMAL, true, true));
		assertThat(strings[0], is("a"));
		assertThat(strings[1], is("b"));
		assertThat(strings[2], is("c"));
	}
	
	@Test
	public void comparatorGenerator_returnsReverseOrderComparatorIfReverseIsPassed() {
		String[] strings = {"a", "b", "c"};
		Arrays.sort(strings, ComparatorUtility.comparatorGenerator(Order.REVERSE, true, true));
		assertThat(strings[0], is("c"));
		assertThat(strings[1], is("b"));
		assertThat(strings[2], is("a"));
	}
	
	@Test
	public void comparatorGenerator_returnsCaseSensitiveComparatorIfTrueIsPassedAsSecondParam() {
		String[] strings = {"a", "b", "A", "B"};
		Arrays.sort(strings, ComparatorUtility.comparatorGenerator(Order.NORMAL, true, true));
		assertThat(strings[0], is("A"));
		assertThat(strings[1], is("B"));
		assertThat(strings[2], is("a"));
		assertThat(strings[3], is("b"));
	}

	@Test
	public void comparatorGenerator_returnsCaseIgnoreComparatorIfFalseIsPassedAsSecondParam() {
		String[] strings = {"a", "A"};
		Arrays.sort(strings, ComparatorUtility.comparatorGenerator(Order.NORMAL, false, true));
		assertThat(strings[0], is("a"));
		assertThat(strings[1], is("A"));
	}
	
	@Test
	public void comparatorGenerator_returnsIncludingSpaceComparatorIfTrueIsPassedAsThirdParam() {
		String[] strings = {"a", " "};
		Arrays.sort(strings, ComparatorUtility.comparatorGenerator(Order.NORMAL, true, true));
		assertThat(strings[0], is(" "));
		assertThat(strings[1], is("a"));
	}
	
	@Test
	public void comparatorGenerator_returnsExcludingSpaceComparatorIfFalseIsPassedAsThirdParam() {
		String[] strings = {"a", " b"};
		Arrays.sort(strings, ComparatorUtility.comparatorGenerator(Order.NORMAL, true, false));
		assertThat(strings[0], is("a"));
		assertThat(strings[1], is(" b"));
	}
}
