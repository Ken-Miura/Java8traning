/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex11;

import java.util.Objects;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public final class ImageUtility {
	
	private ImageUtility () {
		throw new AssertionError("cannot instanciate");
	}
	
	public static ColorTransformer compose(ColorTransformer ct1, ColorTransformer ct2) {
		return (x, y, c)->{ return ct2.apply(x, y, ct1.apply(x, y, c)); };
	}
	
	public static UnaryOperator<Color> brighten (double factor) {
		return c->c.deriveColor(0, 1, factor, 1);
	}
	
	public static ColorTransformer toColorTransformer(UnaryOperator<Color> op) {
		return (x, y, c)->{ return op.apply(c); };
	}
	
	public static Image transform (Image in, ColorTransformer colorTransformer) {
		Objects.requireNonNull(in, "in must not be null");
		Objects.requireNonNull(colorTransformer, "colorTransformer must not be null");
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				out.getPixelWriter().setColor(x, y, 
						colorTransformer.apply(x, y, in.getPixelReader().getColor(x, y)));
			}
		}
		return out;
	}
	
	public static ColorTransformer surroundByColorFrame(int width, int height, int frameSize, Color color){
		if (width<0 || height<0 || frameSize<0) {
			throw new IllegalArgumentException("width, height and frameSize must be 0 or more");
		}
		Objects.requireNonNull(color, "color must not be null");
		return (x, y, c)->{
			if (x<frameSize || x>(width-frameSize)) {
				return color;	
			}
			if (y<frameSize || y>(height-frameSize)) {
				return color;	
			}
			return c;
		};
	}
	
	@FunctionalInterface
	public static interface ColorTransformer {
		public Color apply (int x, int y, Color colorAtXY);
	}
}
