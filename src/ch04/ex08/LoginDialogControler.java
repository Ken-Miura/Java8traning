/**
 * Copyright 2016 Ken Mirua
 */
package ch04.ex08;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public final class LoginDialogControler implements Initializable {

	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button okButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		okButton.disableProperty().bind(
				Bindings.createBooleanBinding(()->{return (username.getText().length()==0 || password.getText().length()==0);}, username.textProperty(), password.textProperty()));
		okButton.setOnAction(event->System.out.print("Verifying " + username.getText() + ": " + password.getText()));
	}

}
