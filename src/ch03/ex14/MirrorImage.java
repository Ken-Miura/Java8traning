/**
 * Copyright 2016 Ken Mirua
 */
package ch03.ex14;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public final class MirrorImage extends Application {

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

		ColorTransformer mirror = (x, y, reader) -> {
			return reader.getColor((int)((image.getWidth()-1)-x), y);
		};
		
		Image transformedImage = LatentImage.from(image)
									.transform(mirror)
									.toImage();
		
		imageView.setImage(transformedImage);

		Group root = new Group();
		root.getChildren().add(imageView);
		Scene scene = new Scene(root);

	    primaryStage.setTitle(MirrorImage.class.getSimpleName());
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
