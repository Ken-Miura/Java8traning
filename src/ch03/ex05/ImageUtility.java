package ch03.ex05;

import java.util.Objects;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public final class ImageUtility {

	private ImageUtility () {
		throw new AssertionError("cannot instanciate");
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
	
	@FunctionalInterface
	public static interface ColorTransformer {
		public Color apply (int x, int y, Color colorAtXY);
	}
}
