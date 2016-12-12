/**
 * Copyright 2016 Ken Mirua
 */
package ch04.ex05;

import org.junit.Test;

import javafx.beans.property.SimpleStringProperty;

public class BindingsUtilityTest {

	@Test(expected=NullPointerException.class)
	public void observe1_throwsNullPointerExceptionIfNullIsPassedAsFirstParam() {
		BindingsUtility.observe(null, new SimpleStringProperty());
	}
	
	@Test(expected=NullPointerException.class)
	public void observe1_throwsNullPointerExceptionIfNullIsPassedAsSecondParam() {
		BindingsUtility.observe(s->s, null);
	}
	
	@Test(expected=NullPointerException.class)
	public void observe2_throwsNullPointerExceptionIfNullIsPassedAsFirstParam() {
		BindingsUtility.observe(null, new SimpleStringProperty(), new SimpleStringProperty());
	}
	
	@Test(expected=NullPointerException.class)
	public void observe2_throwsNullPointerExceptionIfNullIsPassedAsSecondParam() {
		BindingsUtility.observe((s, t)->s, null, new SimpleStringProperty());
	}
	
	@Test(expected=NullPointerException.class)
	public void observe2_throwsNullPointerExceptionIfNullIsPassedAsThirdParam() {
		BindingsUtility.observe((s, t)->s, new SimpleStringProperty(), null);
	}
}
