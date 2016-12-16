/**
 * Copyright 2016 Ken Mirua
 */
package ch04.ex08;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Java FXのコントロールではなく、普通のJavaのオブジェクトをfxmlから作ることがこの問題の趣旨らしいのでこの回答は間違い。時間取れれば試したい。TODO
public final class LoginDialog extends Application  {

	public static void main (String... args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("dialog.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

}
