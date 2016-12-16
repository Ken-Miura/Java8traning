/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex07;

import java.util.Comparator;
import java.util.Objects;

public final class ComparatorUtility {

	public static enum Order {
		NORMAL, REVERSE
	}
	
	private ComparatorUtility () {
		throw new AssertionError("cannot instanciate");
	}

	public static Comparator<String> reverse(Comparator<String> comparator) {
		Objects.requireNonNull(comparator, "comparator must not be null");
		return (c1, c2)-> { return comparator.compare(c2, c1); };
	}
	
	public static Comparator<String> ignoreCase(Comparator<String> comparator) {
		Objects.requireNonNull(comparator, "comparator must not be null");
		return (c1, c2)-> { return comparator.compare(c2.toLowerCase().toUpperCase(), c1.toLowerCase().toUpperCase()); };
	}
	
	public static Comparator<String> excludeSpace(Comparator<String> comparator) {
		Objects.requireNonNull(comparator, "comparator must not be null");
		return (c1, c2)-> { return comparator.compare(c2.replaceAll("\\s", ""), c1.replaceAll("\\s", "")); };
	}
	
	public static Comparator<String> comparatorGenerator(Order order, boolean caseSensitive, boolean includeSpace) {
		switch (order) {
		case NORMAL:
			if (caseSensitive) {
				if (includeSpace) {
					return (s1, s2)->{ return s1.compareTo(s2); };
				} else {
					return (s1, s2)->{ return s1.replaceAll("\\s", "").compareTo(s2.replaceAll("\\s", "")); };
				}
			} else {
				if (includeSpace) {
					return (s1, s2)->{ return s1.toLowerCase().compareTo(s2.toLowerCase()); };
				} else {
					return (s1, s2)->{ return s1.replaceAll("\\s", "").toLowerCase().compareTo(s2.replaceAll("\\s", "").toLowerCase()); };
				}
			}
		case REVERSE:
			if (caseSensitive) {
				if (includeSpace) {
					return (s1, s2)->{ return s2.compareTo(s1); };
				} else {
					return (s1, s2)->{ return s2.replaceAll("\\s", "").compareTo(s1.replaceAll("\\s", "")); };
				}
			} else {
				if (includeSpace) {
					return (s1, s2)->{ return s2.toLowerCase().compareTo(s1.toLowerCase()); };
				} else {
					return (s1, s2)->{ return s2.replaceAll("\\s", "").toLowerCase().compareTo(s1.replaceAll("\\s", "").toLowerCase()); };
				}
			}
		default:
			throw new AssertionError();	
		}
	}
}
