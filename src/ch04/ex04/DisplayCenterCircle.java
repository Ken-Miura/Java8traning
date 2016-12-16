/**
 * Copyright 2016 Ken Mirua
 */
package ch04.ex04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public final class DisplayCenterCircle extends Application {

	private static final double INITIAL_RADIUS = 10.0;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Circle circle = new Circle(INITIAL_RADIUS);
		
		Group root = new Group();
		root.getChildren().add(circle);
		Scene scene = new Scene(root);

		circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
		circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2));
		
		circle.radiusProperty().bind(Bindings.divide(Bindings.min(scene.widthProperty(), scene.heightProperty()), 2));
		
	    primaryStage.setTitle(DisplayCenterCircle.class.getSimpleName());
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
