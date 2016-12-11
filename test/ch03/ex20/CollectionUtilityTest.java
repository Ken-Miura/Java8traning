/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex20;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class CollectionUtilityTest {

	@Test(expected=NullPointerException.class)
	public void map_throwsNullPointerExceptionIfNullIsPassedAsFirstParam() {
		CollectionUtility.map(null, (Object o)->o);
	}

	@Test(expected=NullPointerException.class)
	public void map_throwsNullPointerExceptionIfNullIsPassedAsSecondParam() {
		CollectionUtility.map(new ArrayList<Object>(), null);
	}
	
	@Test
	public void map_returnsValueMappedList() {
		List<String> list = Arrays.asList("Alice", "Bob", "Charlie");
		List<Character> mappedList = CollectionUtility.map(list, s->s.charAt(0));
		
		assertThat(mappedList, is(Arrays.asList('A', 'B', 'C')));
	}
}
