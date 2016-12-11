/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex15;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public final class LatentImage {

	private final Image in;
	private final List<ColorTransformer> pendingOperations = new ArrayList<>();
	
	private LatentImage (Image in) {
		Objects.requireNonNull(in, "in must not be null");
		this.in = in;
	}
	
	public static LatentImage from (Image image) {
		return new LatentImage(image);
	}
	
	public LatentImage transform (ColorTransformer f) {
		Objects.requireNonNull(f, "f must not be null");
		pendingOperations.add(f);
		return this;
	}
	
	public LatentImage transform (UnaryOperator<Color> f) {
		return transform(toColorTransformer(f));
	}
	
	private ColorTransformer toColorTransformer(UnaryOperator<Color> op) {
		return (x, y, c)->{ return op.apply(c); };
	}
	
	public Image toImage () throws InterruptedException {
		int n = Runtime.getRuntime().availableProcessors();
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		Color[][] colors = new Color[width][height];
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				colors[x][y] = in.getPixelReader().getColor(x, y);
			}
		}
		ExecutorService pool = null;
		try {
			pool = Executors.newCachedThreadPool();
			for (int i=0; i<n; i++) {
				int fromY = i * (height / n);
				int toY = (i+1) * (height / n);
				pool.submit(()->{
					for (int x=0; x<width; x++) {
						for (int y=fromY; y<toY; y++) {
							for (ColorTransformer f: pendingOperations) {
								colors[x][y] = f.apply(x, y, colors[x][y]);
							}
						}
					}
				});
			}
		} finally {
			if (pool!=null) {
				pool.shutdown();
				pool.awaitTermination(1, TimeUnit.HOURS);
			}
		}
		WritableImage out = new WritableImage(width, height);
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				out.getPixelWriter().setColor(x, y, colors[x][y]);
			}
		}
		return out;
	}
}
