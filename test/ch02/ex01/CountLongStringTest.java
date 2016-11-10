package ch02.ex01;

import java.util.List;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class CountLongStringTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void numOfLongString_throwsNullPointerExceptionIfNullIsPassed() throws InterruptedException {
		exception.expect(NullPointerException.class);
		exception.expectMessage("words must not be null");
		
		CountLongString.numOfLongString(null);
	}
	
	@Test
	public void numOfLongString_returns0IfEmptyListIsPassed() throws InterruptedException {
		List<String> words = new ArrayList<>();
		
		final int result = CountLongString.numOfLongString(words);
		
		assertThat(result, is(0));
	}
	
	@Test
	public void numOfLongString_returns0IfStringWhichLength12IsPassed() throws InterruptedException {
		List<String> words = new ArrayList<>();
		words.add("0123456789ab");
		
		final int result = CountLongString.numOfLongString(words);
		
		assertThat(result, is(0));
	}
	
	@Test
	public void numOfLongString_returns1IfStringWhichLength13IsPassed() throws InterruptedException {
		List<String> words = new ArrayList<>();
		words.add("0123456789abc");
		
		final int result = CountLongString.numOfLongString(words);
		
		assertThat(result, is(1));
	}
	
	@Test
	public void numOfLongString_returns3If3LongStringArePassed() throws InterruptedException {
		List<String> words = new ArrayList<>();
		words.add("longlonglonglonglongString");
		words.add("shortString");
		words.add("toooooooooooooooooooooooLongString");
		words.add("short");
		words.add("toooooooooooooooooLongString");
		
		final int result = CountLongString.numOfLongString(words);
		
		assertThat(result, is(3));
	}

}
