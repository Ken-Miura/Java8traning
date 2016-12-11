/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex13;

import javafx.scene.paint.Color;

@FunctionalInterface
public interface ConvolutionFilter {
	public Color apply (int x, int y, Color[][] matrix);
}