/**
 * Copyright 2017 Ken Mirua
 */
package ch09.ex08;

public class Point implements Comparable<Point> {

	private final int x;
	private final int y;
	
	public Point (int x, int y) {
		this.x = x;
		this.y = y;
	}

	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}

	@Override
	public int compareTo(Point other) { // 問題指定により、Ingeter.compare(int, int)を利用してはいけない
		int diff = Integer.valueOf(x).compareTo(other.getX());
		if (diff != 0) {
			return diff;
		}
		return Integer.valueOf(y).compareTo(other.getY());
	}	
}
