/**
 * Copyright 2016 Ken Mirua
 */
package ch04.ex07;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public final class LoginScreenTest extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		final double rem = new Text("").getLayoutBounds().getHeight();
		
		Label usernameLabel = new Label("User name: ");
		TextField username = new TextField();
		Label passwordLabel = new Label("Password: ");
		PasswordField password = new PasswordField();
		
		GridPane pane = new GridPane();
		pane.add(usernameLabel, 0, 0);
		GridPane.setHalignment(usernameLabel, HPos.RIGHT);
		pane.add(username, 1, 0);
		pane.add(passwordLabel, 0, 1);
		GridPane.setHalignment(passwordLabel, HPos.RIGHT);
		pane.add(password, 1, 1);
		
		HBox buttons = new HBox(0.5 * rem);
		Button okButton = new Button("Ok");
		Button cancelButton = new Button("Cancel");
		buttons.getChildren().add(okButton);
		buttons.getChildren().add(cancelButton);
		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(0.2 * rem));
		pane.add(buttons, 0, 2, 2, 1);
		
		pane.setHgap(0.8 * rem);
		pane.setVgap(0.8 * rem);
		pane.setPadding(new Insets(0.8 * rem));
		
		pane.setGridLinesVisible(true);
		// CSS
		//buttons.setStyle("-fx-border-color: red;");
		// CSSを使用しない方法
		buttons.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,  CornerRadii.EMPTY,  BorderWidths.DEFAULT)));
		
		Scene scene = new Scene(pane);
	    primaryStage.setScene(scene);
	    primaryStage.setTitle(LoginScreenTest.class.getSimpleName());
	    primaryStage.setResizable(false);
	    primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
