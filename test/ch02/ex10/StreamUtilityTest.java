package ch02.ex10;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;

public class StreamUtilityTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void calculateAverage_throwsNullPointerExceptionIfNullIsPassed() {
		exception.expect(NullPointerException.class);
		exception.expectMessage("stream must not be null");
		
		StreamUtility.calculateAverage(null);
	}
	
	@Test
	public void calculateAverage_returns0IfEmptyStreamIsPassed() {
		double result = StreamUtility.calculateAverage(Stream.empty());
		
		assertThat(result, is(0.0));
	}
	
	@Test
	public void calculateAverage_returnsAverageIfDoubleStreamIsPassed() {
		double result = StreamUtility.calculateAverage(Stream.of(1.0, 2.0, 3.0));
		
		assertThat(result, is(2.0));
	}
}
