package ch02.ex06;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class StreamUtilityTest {

	@Test
	public void characterStream_returnsOrderedCharacterStreamIfWordIsPassed() {
		Stream<Character> charStream = StreamUtility.characterStream("boat");
		
		List<Character> charList = charStream.collect(Collectors.toList());
		assertThat(charList.get(0), is('b'));
		assertThat(charList.get(1), is('o'));
		assertThat(charList.get(2), is('a'));
		assertThat(charList.get(3), is('t'));
	}

}
