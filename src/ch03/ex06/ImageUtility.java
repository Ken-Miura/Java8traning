/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex06;

import java.util.Objects;
import java.util.function.BiFunction;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public final class ImageUtility {
	private ImageUtility () {
		throw new AssertionError("cannot instanciate");
	}
	
	public static <T> Image transform (Image in, BiFunction<Color, T, Color> biBunction, T arg) {
		Objects.requireNonNull(in, "in must not be null");
		Objects.requireNonNull(biBunction, "biBunction must not be null");
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				out.getPixelWriter().setColor(x, y, 
						biBunction.apply(in.getPixelReader().getColor(x, y), arg));
			}
		}
		return out;
	}
}
