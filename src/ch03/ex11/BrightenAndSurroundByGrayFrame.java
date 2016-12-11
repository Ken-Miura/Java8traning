/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex11;

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

public final class BrightenAndSurroundByGrayFrame extends Application {

	private static final int FRAME_SIZE = 10; 
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parameters params = getParameters();
		List<String> args = params.getRaw();
		if (args.size() != 2) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error");
			alert.setContentText("プログラム起動時に画像ファイルのURIとどれくらい明るくするかの数字を引数に与えてください。");
			alert.showAndWait();
			System.exit(0);
		}
		ImageView imageView = new ImageView();
		Image image = new Image(args.get(0));
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		double factor = Double.valueOf(args.get(1));
		Image transformedImage = ColorTransformer.transform(image, 
				ColorTransformer.compose(ColorTransformer.toColorTransformer(ColorTransformer.brighten(factor)), ColorTransformer.surroundByColorFrame(width, height, FRAME_SIZE, Color.GRAY)));
		imageView.setImage(transformedImage);

		Group root = new Group();
		root.getChildren().add(imageView);
		Scene scene = new Scene(root);

	    primaryStage.setTitle(BrightenAndSurroundByGrayFrame.class.getSimpleName());
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
