package ch02.ex08;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class StreamUtilityTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void zip_throwsNullPointerExceptionIfNullIsPassedAsFirstParam() {
		exception.expect(NullPointerException.class);
		exception.expectMessage("first must not be null");
		
		StreamUtility.zip(null, Stream.of(1));
	}

	@Test
	public void zip_throwsNullPointerExceptionIfNullIsPassedAsSecondParam() {
		exception.expect(NullPointerException.class);
		exception.expectMessage("second must not be null");
		
		StreamUtility.zip(Stream.of(1), null);
	}
	
	@Test
	public void zip_returnsEmptyStreamIfEmptyStreamIsPassedAsFirstParam() {
		Stream<Integer> integerStream = StreamUtility.zip(Stream.empty(), Stream.of(1));
		long count = integerStream.count();
		
		assertThat(count, is(0L));
	}
	
	@Test
	public void zip_returnsEmptyStreamIfEmptyStreamIsPassedAsSecondParam() {
		Stream<Integer> integerStream = StreamUtility.zip(Stream.of(1), Stream.empty());
		long count = integerStream.count();
		
		assertThat(count, is(0L));
	}
	
	@Test
	public void zip_returnsEmptyStreamIfEmptyStreamIsPassedAsFirstAndSecondParam() {
		Stream<Integer> integerStream = StreamUtility.zip(Stream.empty(), Stream.empty());
		long count = integerStream.count();
		
		assertThat(count, is(0L));
	}
	
	@Test
	public void zip_returnsAlternatelyMixedStreamIfTwoStreamsArePassed() {
		List<Integer> list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		List<Integer> list2 = new ArrayList<>();
		list2.add(4);
		list2.add(5);
		list2.add(6);
		
		Stream<Integer> resultStream = StreamUtility.zip(list1.stream(), list2.stream());
		List<Integer> resultList = resultStream.collect(Collectors.toList());
		
		assertThat(resultList.get(0), is(1));
		assertThat(resultList.get(1), is(4));
		assertThat(resultList.get(2), is(2));
		assertThat(resultList.get(3), is(5));
		assertThat(resultList.get(4), is(3));
		assertThat(resultList.get(5), is(6));
	}
	
	@Test
	public void zip_returnsStreamWhichSizeIsTwiceAsSmallestStreamIfTwoDifferentSizeStreamsArePassed() {
		List<Integer> smallerList = new ArrayList<>();
		smallerList.add(1);
		smallerList.add(2);
		smallerList.add(3);
		List<Integer> biggerList = new ArrayList<>();
		biggerList.add(4);
		biggerList.add(5);
		biggerList.add(6);
		biggerList.add(7);
		
		Stream<Integer> resultStream = StreamUtility.zip(smallerList.stream(), biggerList.stream());
		List<Integer> resultList = resultStream.collect(Collectors.toList());
		
		assertThat(resultList.get(0), is(1));
		assertThat(resultList.get(1), is(4));
		assertThat(resultList.get(2), is(2));
		assertThat(resultList.get(3), is(5));
		assertThat(resultList.get(4), is(3));
		assertThat(resultList.get(5), is(6));
		
		assertThat(resultList.size(), is(smallerList.size()*2));
	}
}
