/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex14;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

class DelayedPixelReader implements PixelReader {

	private final PixelReader originalReader;
	private final Color[][] cache;
	private final List<ColorTransformer> pendingOperations = new ArrayList<>();
	
	DelayedPixelReader(Image image) {
		Objects.requireNonNull(image, "Image must not be null");
		this.originalReader = image.getPixelReader();
		cache = new Color[(int)image.getWidth()][(int)image.getHeight()];
	}
	
	void addPendingOperation (ColorTransformer f) {
		pendingOperations.add(f);
	}
	
	@SuppressWarnings("rawtypes" )
	@Override
	public PixelFormat getPixelFormat() {
		return originalReader.getPixelFormat();
	}

	@Override
	public int getArgb(int x, int y) {
		return originalReader.getArgb(x, y);
	}

	@Override
	public Color getColor(int x, int y) {
		if (cache[x][y] != null) {
			return cache[x][y];
		}
		Color c = originalReader.getColor(x, y);
		for (ColorTransformer f: pendingOperations) {
			c = f.apply(x, y, originalReader);
		}
		cache[x][y] = c;
		return c;
	}

	@Override
	public <T extends Buffer> void getPixels(int x, int y, int w, int h, WritablePixelFormat<T> pixelformat, T buffer,
			int scanlineStride) {
		originalReader.getPixels(x, y, w, h, pixelformat, buffer, scanlineStride);
	}

	@Override
	public void getPixels(int x, int y, int w, int h, WritablePixelFormat<ByteBuffer> pixelformat, byte[] buffer,
			int offset, int scanlineStride) {
		originalReader.getPixels(x, y, w, h, pixelformat, buffer, offset, scanlineStride);
	}

	@Override
	public void getPixels(int x, int y, int w, int h, WritablePixelFormat<IntBuffer> pixelformat, int[] buffer,
			int offset, int scanlineStride) {
		originalReader.getPixels(x, y, w, h, pixelformat, buffer, offset, scanlineStride);
	}

}
