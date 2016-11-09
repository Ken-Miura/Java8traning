package ch01.ex09;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

public class Collection2Test {

	@Test
	public void forEachIf_ProcessesElementIfConditionIsSatisfied() {
		final Collection2<StringBuilder> testedCollection = new ArrayList2<>();
		testedCollection.add(new StringBuilder("test"));
		
		testedCollection.forEachIf((s)->s.replace(0, s.length(), "expected"), (t)->t.toString().equals("test"));
		
		for (StringBuilder s: testedCollection) {
			assertEquals("expected", s.toString());	
		}
	}
	
	@Test
	public void forEachIf_DoesNotProcessesElementIfConditionIsNotSatisfied() {
		final Collection2<StringBuilder> testedCollection = new ArrayList2<>();
		testedCollection.add(new StringBuilder("expected"));
		
		testedCollection.forEachIf((s)->s.replace(0, s.length(), "not match"), (t)->t.toString().equals("test"));
		
		for (StringBuilder s: testedCollection) {
			assertEquals("expected", s.toString());	
		}
	}
	
	// テスト用に利用するクラス
	private static class ArrayList2<E> implements Collection2<E> {

		private final List<E> list = new ArrayList<>();

		@Override
		public boolean add(E e) {
			return list.add(e);
		}

		@Override
		public boolean addAll(Collection<? extends E> c) {
			return list.addAll(c);
		}

		@Override
		public void clear() {
			list.clear();
		}

		@Override
		public boolean contains(Object o) {
			return list.contains(o);
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return list.containsAll(c);
		}

		@Override
		public boolean isEmpty() {
			return list.isEmpty();
		}

		@Override
		public Iterator<E> iterator() {
			return list.iterator();
		}

		@Override
		public boolean remove(Object o) {
			return list.remove(o);
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			return list.removeAll(c);
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			return list.retainAll(c);
		}

		@Override
		public int size() {
			return list.size();
		}

		@Override
		public Object[] toArray() {
			return list.toArray();
		}

		@Override
		public <T> T[] toArray(T[] a) {
			return list.toArray(a);
		}
	}
}
