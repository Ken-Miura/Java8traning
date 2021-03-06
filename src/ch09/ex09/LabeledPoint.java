/**
 * Copyright 2017 Ken Mirua
 */
package ch09.ex09;

import java.util.Objects;

public class LabeledPoint {

	private final String label;
	private final int x;
	private final int y;
	
	public LabeledPoint(String label, int x, int y) {
		this.label = label;
		this.x = x;
		this.y = y;
	}

	public final String getLabel() {
		return label;
	}

	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(label, x, y);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LabeledPoint other = (LabeledPoint) obj;
		return Objects.equals(label, other.getLabel()) &&
				Objects.equals(x, other.getX()) &&
				Objects.equals(y, other.getY());
	}

// 以下eclipseの自動生成で出力されたhashCodeとequals
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((label == null) ? 0 : label.hashCode());
//		result = prime * result + x;
//		result = prime * result + y;
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		LabeledPoint other = (LabeledPoint) obj;
//		if (label == null) {
//			if (other.label != null)
//				return false;
//		} else if (!label.equals(other.label))
//			return false;
//		if (x != other.x)
//			return false;
//		if (y != other.y)
//			return false;
//		return true;
//	}
	
}
