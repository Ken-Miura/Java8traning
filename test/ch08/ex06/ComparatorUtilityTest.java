/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex06;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import java.util.Comparator;
import org.junit.Test;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class ComparatorUtilityTest {

	@Test
	public void totalOrderingPoint2DComparator_returnstotalOrderingComparatorForPoint2D() {
		final Point2D p0 = new Point2D(0.0, 0.0);
		final Point2D p1 = new Point2D(0.0, 0.0);
		final Point2D p2 = new Point2D(1.0, 0.0);
		final Point2D p3 = new Point2D(0.0, 1.0);
		final Point2D p4 = new Point2D(1.0, 1.0);
		
		Comparator<Point2D> comparator = ComparatorUtility.totalOrderingPoint2DComparator();
		
		assertThat(comparator.compare(p0, p1), is(0));
		assertThat(comparator.compare(p0, p2), is(not(0)));
		assertThat(comparator.compare(p0, p3), is(not(0)));
		assertThat(comparator.compare(p0, p4), is(not(0)));
	}

	@Test
	public void totalOrderingRectangle2DComparator_returnstotalOrderingComparatorForRectangle2D() {
		final Rectangle2D r0 = new Rectangle2D(0.0, 0.0, 1.0, 1.0);
		final Rectangle2D r1 = new Rectangle2D(0.0, 0.0, 1.0, 1.0);
		final Rectangle2D r2 = new Rectangle2D(1.0, 0.0, 1.0, 1.0);
		final Rectangle2D r3 = new Rectangle2D(0.0, 1.0, 1.0, 1.0);
		final Rectangle2D r4 = new Rectangle2D(0.0, 0.0, 0.5, 1.0);
		final Rectangle2D r5 = new Rectangle2D(0.0, 0.0, 1.0, 0.5);
		final Rectangle2D r6 = new Rectangle2D(1.0, 1.0, 0.5, 0.5);
		
		Comparator<Rectangle2D> comparator = ComparatorUtility.totalOrderingRectangle2DComparator();
		
		assertThat(comparator.compare(r0, r1), is(0));
		assertThat(comparator.compare(r0, r2), is(not(0)));
		assertThat(comparator.compare(r0, r3), is(not(0)));
		assertThat(comparator.compare(r0, r4), is(not(0)));
		assertThat(comparator.compare(r0, r5), is(not(0)));
		assertThat(comparator.compare(r0, r6), is(not(0)));
	}

}
