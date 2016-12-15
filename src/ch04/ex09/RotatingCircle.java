/**
 * Copyright 2016 Ken Mirua
 */
package ch04.ex09;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public final class RotatingCircle extends Application {

	private static final int HEIGHT = 320;
	private static final int WIDTH = 480;
	private static final double RADIUS = 10.0;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Circle circle = new Circle(RADIUS);
		circle.setCenterX(WIDTH);
		circle.setCenterY(HEIGHT);
		
		Ellipse ellipse = new Ellipse();
		ellipse.setCenterX(WIDTH/2);
		ellipse.setCenterY(HEIGHT/2);
		ellipse.setRadiusX(100.0f);
		ellipse.setRadiusY(50.0f);
			
		PathTransition pathTransition = new PathTransition(Duration.millis(3000), ellipse);
		pathTransition.setNode(circle);
		pathTransition.setCycleCount(Animation.INDEFINITE);
		pathTransition.play();
		
		Group root = new Group();
		root.getChildren().add(circle);
		Scene scene = new Scene(root, WIDTH, HEIGHT);

	    primaryStage.setTitle(RotatingCircle.class.getSimpleName());
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
