/**
 * Copyright 2016 Ken Mirua
 */
package ch04.ex06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public final class BorderPaneTest extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane pane = new BorderPane();
		
		Button topButton = new Button("Top");
		pane.setTop(topButton);
		BorderPane.setAlignment(topButton, Pos.CENTER);
		
		Button leftButton = new Button("Left");
		pane.setLeft(leftButton);
		BorderPane.setAlignment(leftButton, Pos.CENTER);
		
		pane.setCenter(new Button("Center"));
		
		Button rightButton = new Button("Right");
		pane.setRight(rightButton);
		BorderPane.setAlignment(rightButton, Pos.CENTER);
		
		Button bottomButton = new Button("Bottom");
		pane.setBottom(bottomButton);
		BorderPane.setAlignment(bottomButton, Pos.CENTER);
		
		Scene scene = new Scene(pane);
	    primaryStage.setScene(scene);
	    primaryStage.setTitle(BorderPaneTest.class.getSimpleName());
	    primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
