/**
 * Copyright 2016 Ken Mirua
 */
package ch04.ex02;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import ch04.ex02.Greeting;

public class GreetingTest {

	@Test
	public void getText_returnsText() {
		final String expected = "test";
		Greeting g = new Greeting();
		g.setText(expected);
		
		assertThat(g.getText(), is(expected));
	}
	
	@Test
	public void getText_returnsEmptyTextIfDefault() {
		Greeting g = new Greeting();
		assertThat(g.getText(), is(""));
	}
	
	@Test
	public void textProperty_DoesNotChangeText() {
		Greeting g = new Greeting();
		g.setText("test");
		final String expected = g.getText();
		
		g.textProperty();
		
		assertThat(g.getText(), is(expected));
	}
	
	@Test
	public void textProperty_DoesNotChangeBehaviorOfGetAndSet() {
		final String expected1 = "test1";
		Greeting g = new Greeting();
		g.setText(expected1);
		assertThat(g.getText(), is(expected1));
		
		g.textProperty();
		
		final String expected2 = "test2";
		g.setText(expected2);
		assertThat(g.getText(), is(expected2));
	}
	
}
