/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex13;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public final class BlurImage extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parameters params = getParameters();
		List<String> args = params.getRaw();
		if (args.size() != 1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error");
			alert.setContentText("プログラム起動時に画像ファイルのURIを引数に与えてください。");
			alert.showAndWait();
			System.exit(0);
		}
		ImageView imageView = new ImageView();
		Image image = new Image(args.get(0));

		ImageFilter blur = (x, y, w, h, reader) -> {
			if (!(0<x && x<(w-1))) {
				return Color.BLACK;
			}
			if (!(0<y && y<(h-1))) {
				return Color.BLACK;
			}
			double redSum = 0.0;
			double greenSum = 0.0;
			double blueSum = 0.0;
			for (int i=-1; i<=1; i++) {
				for (int j=-1; j<=1; j++) {
					redSum+=reader.getColor(x+i, y+j).getRed();
					greenSum+=reader.getColor(x+i, y+j).getGreen();
					blueSum+=reader.getColor(x+i, y+j).getBlue();
				}	
			}
			final int size = 9;
			return Color.color(redSum/size, greenSum/size, blueSum/size);
		};
		
		Image transformedImage = LatentImage.from(image)
									.transform(blur)
									.transform(blur)
									.transform(blur)
									.transform(blur)
									.transform(blur)
									.toImage();
		
		imageView.setImage(transformedImage);

		Group root = new Group();
		root.getChildren().add(imageView);
		Scene scene = new Scene(root);

	    primaryStage.setTitle(BlurImage.class.getSimpleName());
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
