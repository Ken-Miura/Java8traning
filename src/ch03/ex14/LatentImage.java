/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex14;

import java.util.Objects;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public final class LatentImage {

	private Image in;
	private final DelayedPixelReader delayedPixelReader;
	
	private LatentImage (Image in) {
		Objects.requireNonNull(in, "in must not be null");
		this.in = in;
		delayedPixelReader = new DelayedPixelReader(in);
	}
	
	public static LatentImage from (Image image) {
		return new LatentImage(image);
	}
	
	public LatentImage transform (ColorTransformer f) {
		Objects.requireNonNull(f, "f must not be null");
		delayedPixelReader.addPendingOperation(f);
		return this;
	}
	
	public LatentImage transform (UnaryOperator<Color> f) {
		return transform(toColorTransformer(f));
	}
		
	private ColorTransformer toColorTransformer(UnaryOperator<Color> op) {
		return (x, y, reader)->{ return op.apply(reader.getColor(x, y)); };
	}
	
	public Image toImage () {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				out.getPixelWriter().setColor(x, y, delayedPixelReader.getColor(x, y));
			}
		}
		return out;
	}
}
