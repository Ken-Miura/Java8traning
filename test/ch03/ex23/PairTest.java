/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex23;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

public class PairTest {

	@Test
	public void map_returnsValueMappedPair() {
		Pair<String> pair = new Pair<>("Alice", "Bob");
		Pair<Character> mappedPair = pair.map(s->s.charAt(0));
		assertThat(mappedPair.getKey(), is('A'));
		assertThat(mappedPair.getValue(), is('B'));
	}

}
