/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex13;

import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

@FunctionalInterface
public interface ImageFilter {
	public Color apply (int x, int y, int width, int height, PixelReader reader);
}