/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex07;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class ComparatorUtilityTest {

	@Test
	public void reversedOrderNullsFirst_returnsReversedOrderAndNullsFirstComparator() {
		Integer i0 = null;
		Integer i1 = 0;
		Integer i2 = 1;
		Integer i3 = -1;
		List<Integer> list = new ArrayList<>();
		list.add(i3);
		list.add(i0);
		list.add(i2);
		list.add(i1);
		
		list.sort(ComparatorUtility.reversedOrderNullsFirst());
		//list.sort(Comparator.<Integer>nullsFirst(Comparator.naturalOrder()).reversed());
		
		assertThat(list.get(0), is(i2));
		assertThat(list.get(1), is(i1));
		assertThat(list.get(2), is(i3));
		assertThat(list.get(3), is(i0));
	}
	
	@Test
	public void reversedOrderNullsFirst_returnsSameFunctionComparatorListedInText() {
		Integer i0 = null;
		Integer i1 = 0;
		Integer i2 = 1;
		Integer i3 = -1;
		
		List<Integer> list1 = new ArrayList<>();
		list1.add(i3);
		list1.add(i0);
		list1.add(i2);
		list1.add(i1);
		
		List<Integer> list2 = new ArrayList<>();
		list2.add(i0);
		list2.add(i1);
		list2.add(i3);
		list2.add(i2);
		
		list1.sort(ComparatorUtility.reversedOrderNullsFirst());
		list2.sort(Comparator.<Integer>nullsFirst(Comparator.naturalOrder()).reversed());
		
		assertThat(list1, is(list2));
	}
}
