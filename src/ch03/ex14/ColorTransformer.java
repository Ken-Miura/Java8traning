/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex14;

import java.util.Objects;
import java.util.function.UnaryOperator;

import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorTransformer {

	public Color apply (int x, int y, PixelReader reader);
	
	public static UnaryOperator<Color> brighten (double factor) {
		return c->c.deriveColor(0, 1, factor, 1);
	}
	
	public static ColorTransformer surroundByColorFrame(int width, int height, int frameSize, Color color){
		if (width<0 || height<0 || frameSize<0) {
			throw new IllegalArgumentException("width, height and frameSize must be 0 or more");
		}
		Objects.requireNonNull(color, "color must not be null");
		return (x, y, reader)->{
			if (x<frameSize || x>(width-frameSize)) {
				return color;	
			}
			if (y<frameSize || y>(height-frameSize)) {
				return color;	
			}
			return reader.getColor(x, y);
		};
	}
	
}
