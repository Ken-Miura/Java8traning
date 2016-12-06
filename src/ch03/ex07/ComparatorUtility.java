/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex07;

import java.util.Comparator;

public final class ComparatorUtility {

	public static enum Order {
		NORMAL, REVERSE
	}
	
	private ComparatorUtility () {
		throw new AssertionError("cannot instanciate");
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
