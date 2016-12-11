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
	private final List<ConvolutionFilter> pendingOperations = new ArrayList<>();
	
	private LatentImage (Image in) {
		Objects.requireNonNull(in, "in must not be null");
		this.in = in;
	}
	
	public static LatentImage from (Image image) {
		return new LatentImage(image);
	}
	
	public LatentImage transform (ColorTransformer f) {
		Objects.requireNonNull(f, "f must not be null");
		pendingOperations.add((x, y, matrix)->{ return f.apply(x, y, matrix[1][1]); });
		return this;
	}
	
	public LatentImage transform (UnaryOperator<Color> f) {
		return transform(toColorTransformer(f));
	}
	
	public LatentImage transform (ConvolutionFilter f) {
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
		WritableImage temp = new WritableImage(1+width+1, 1+height+1); // 畳みこみ操作のために左右上下に１ピクセル多く確保
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				temp.getPixelWriter().setColor(x+1, y+1, in.getPixelReader().getColor(x, y));
			}
		}
		
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				Color c = temp.getPixelReader().getColor(x+1, y+1);
				Color[][] matrix = new Color[3][3];
				for (int i=0; i<matrix.length; i++) {
					for (int j=0; j<matrix[0].length; j++) {
						matrix[i][j] = temp.getPixelReader().getColor(x+i, y+j);
					}
				}
				for (final ConvolutionFilter f: pendingOperations) {
						c = f.apply(x, y, matrix);
				}
				temp.getPixelWriter().setColor(x+1, y+1, c);
			}
		}
		return new WritableImage(temp.getPixelReader(), 1, 1, width, height);
	}
}
