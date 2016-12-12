/**
 * Copyright 2016 Ken Mirua
 */
package ch04.ex05;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static ch04.ex05.BindingsUtility.observe;

public final class SlideLeftRight extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Button smaller = new Button("Smaller");
		Slider guage = new Slider(0, 100, 50);
		Button larger = new Button("Larger");
		
		larger.disableProperty().bind(observe(t->t.doubleValue()>=100, guage.valueProperty()));
		smaller.disableProperty().bind(observe(t->t.doubleValue()<=0, guage.valueProperty()));
		
		HBox root = new HBox();
		root.getChildren().add(smaller);
		root.getChildren().add(guage);
		root.getChildren().add(larger);
		Scene scene = new Scene(root);

	    primaryStage.setTitle(SlideLeftRight.class.getSimpleName());
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}


}
