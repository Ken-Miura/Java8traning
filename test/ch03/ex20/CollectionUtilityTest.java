/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex20;


import java.util.ArrayList;

import org.junit.Test;

public class CollectionUtilityTest {

	@Test(expected=NullPointerException.class)
	public void map_throwsNullPointerExceptionIfNullIsPassedAsFirstParam() {
		CollectionUtility.map(null, (Object o)->o);
	}

	@Test(expected=NullPointerException.class)
	public void map_throwsNullPointerExceptionIfNullIsPassedAsSecondParam() {
		CollectionUtility.map(new ArrayList<Object>(), null);
	}
	
}
