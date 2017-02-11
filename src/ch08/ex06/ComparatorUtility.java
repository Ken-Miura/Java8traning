/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex06;

import java.util.Comparator;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public final class ComparatorUtility {

	private ComparatorUtility() {
		throw new AssertionError("cannot instanciate");
	}
	
	public static Comparator<Point2D> totalOrderingPoint2DComparator() {
		return Comparator.comparing(Point2D::getX).thenComparing(Point2D::getY);
	}
	
	public static Comparator<Rectangle2D> totalOrderingRectangle2DComparator() {
		return Comparator.comparing(Rectangle2D::getMinX).thenComparing(Rectangle2D::getMinY)
				.thenComparing(Rectangle2D::getWidth).thenComparing(Rectangle2D::getHeight);
	}
}
