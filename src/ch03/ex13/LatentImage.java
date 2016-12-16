/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex13;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public final class LatentImage {

	private Image in;
	private final List<ImageFilter> pendingOperations = new ArrayList<>();
	
	private LatentImage (Image in) {
		Objects.requireNonNull(in, "in must not be null");
		this.in = in;
	}
	
	public static LatentImage from (Image image) {
		return new LatentImage(image);
	}
	
	public LatentImage transform (ColorTransformer f) {
		Objects.requireNonNull(f, "f must not be null");
		pendingOperations.add((x, y, w, h, reader)->{ return f.apply(x, y, reader.getColor(x, y)); });
		return this;
	}
	
	public LatentImage transform (UnaryOperator<Color> f) {
		return transform(toColorTransformer(f));
	}
	
	public LatentImage transform (ImageFilter f) {
		Objects.requireNonNull(f, "f must not be null");
		// 前段までのものを強制
		if (pendingOperations.size() != 0) {
			in = toImage();
			pendingOperations.clear();
		}
		pendingOperations.add(f);
		return this;
	}
	
	private ColorTransformer toColorTransformer(UnaryOperator<Color> op) {
		return (x, y, c)->{ return op.apply(c); };
	}
	
	public Image toImage () {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				for (final ImageFilter f: pendingOperations) {
					c = f.apply(x, y, width, height, in.getPixelReader());
				}
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		return out;
	}
}
