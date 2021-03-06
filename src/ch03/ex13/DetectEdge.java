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

public final class DetectEdge extends Application {

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

		ImageFilter detectEdge = (x, y, w, h, reader) -> {
			if (!(0<x && x<(w-1))) {
				return Color.BLACK;
			}
			if (!(0<y && y<(h-1))) {
				return Color.BLACK;
			}
			double red = 4 * reader.getColor(x, y).getRed() - reader.getColor(x, y+1).getRed() - reader.getColor(x-1, y).getRed() - reader.getColor(x+1, y).getRed() - reader.getColor(x, y-1).getRed();
			double green = 4 * reader.getColor(x, y).getGreen() - reader.getColor(x, y+1).getGreen() - reader.getColor(x-1, y).getGreen() - reader.getColor(x+1, y).getGreen() - reader.getColor(x, y-1).getGreen();
			double blue = 4 * reader.getColor(x, y).getBlue() - reader.getColor(x, y+1).getBlue() - reader.getColor(x-1, y).getBlue() - reader.getColor(x+1, y).getBlue() - reader.getColor(x, y-1).getBlue();
			return Color.color(red<0.0?0.0:red>1.0?1.0:red, green<0.0?0.0:green>1.0?1.0:green, blue<0.0?0.0:blue>1.0?1.0:blue);
		};
		
		Image transformedImage = LatentImage.from(image)
									.transform(detectEdge)
									.toImage();
		
		imageView.setImage(transformedImage);

		Group root = new Group();
		root.getChildren().add(imageView);
		Scene scene = new Scene(root);

	    primaryStage.setTitle(DetectEdge.class.getSimpleName());
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
