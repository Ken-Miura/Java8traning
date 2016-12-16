/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex06;

import java.util.Objects;
import java.util.function.BiFunction;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public interface ColorTransformer {
	
	public static <T> Image transform (Image in, BiFunction<Color, T, Color> biFunction, T arg) {
		Objects.requireNonNull(in, "in must not be null");
		Objects.requireNonNull(biFunction, "biFunction must not be null");
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				out.getPixelWriter().setColor(x, y, 
						biFunction.apply(in.getPixelReader().getColor(x, y), arg));
			}
		}
		return out;
	}
}
