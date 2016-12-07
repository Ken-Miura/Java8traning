/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex09;

import java.util.Comparator;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class ComparatorUtilityTest {

	// テスト用クラス
	private static class Name {
		@SuppressWarnings("unused")
		private final String lastname;
		@SuppressWarnings("unused")
		private final String firstname;
		
		Name (String lastname, String firstname) {
			this.lastname = lastname;
			this.firstname = firstname;
		}
	}
	
	@Test
	public void lexicographicComparator_returnsComparatorWhichReturns0IfValuesOfEachFieldAreSame() {
		Comparator<Name> nameComparator = ComparatorUtility.lexicographicComparator("lastname", "firstname");
		Name name1 = new Name("A", "B");
		Name name2 = new Name("A", "B");
		int result = nameComparator.compare(name1, name2);
		
		assertThat(result, is(0));
	}
	
	@Test
	public void lexicographicComparator_returnsComparatorWhichReturnsDifference1() {
		Comparator<Name> nameComparator = ComparatorUtility.lexicographicComparator("lastname", "firstname");
		Name name1 = new Name("A", "B");
		Name name2 = new Name("C", "D");
		int result = nameComparator.compare(name1, name2);
		
		assertThat(result, is("A".compareTo("C")));
	}
	
	@Test
	public void lexicographicComparator_returnsComparatorWhichReturnsDifference2() {
		Comparator<Name> nameComparator = ComparatorUtility.lexicographicComparator("lastname", "firstname");
		Name name1 = new Name("A", "B");
		Name name2 = new Name("A", "C");
		int result = nameComparator.compare(name1, name2);
		
		assertThat(result, is("B".compareTo("C")));
	}
}
