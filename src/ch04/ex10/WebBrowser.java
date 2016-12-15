/**
 * Copyright 2016 Ken Mirua
 */
package ch04.ex10;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public final class WebBrowser extends Application {
	
	private static final String HOME_PAGE_URL = "http://www.google.com";
	private static final double PADDING = 5.0;
	private static final double SPACING = 5.0;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Label urlLabel = new Label("URL: ");
		TextField url = new TextField();
		Button backbutton = new Button("back");
		HBox bar = new HBox(SPACING);
		bar.getChildren().add(urlLabel);
		bar.getChildren().add(url);
		bar.getChildren().add(backbutton);
		bar.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
		
		WebView webView = new WebView();
		WebEngine webEngine = webView.getEngine();
		webEngine.load(HOME_PAGE_URL);
		
		url.setOnAction(e->{
			final String urlText =  url.getText();
			if (urlText.startsWith("http://") || urlText.startsWith("https://")) {
				webEngine.load(urlText);
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Error");
				alert.setContentText("URLが不正です。http://で始まる文字列を入れてください。");
				alert.showAndWait();
			}
		});
		
		backbutton.setOnAction(e->{
			final WebHistory history = webEngine.getHistory();
		    int currentIndex = history.getCurrentIndex();
		    if (currentIndex > 0) {
		    	history.go(-1);
		    }
		});
		
		VBox pane = new VBox();
		pane.getChildren().add(bar);
		pane.getChildren().add(webView);
		
		Scene scene = new Scene(pane);
	    primaryStage.setScene(scene);
	    primaryStage.setTitle(WebBrowser.class.getSimpleName());
	    primaryStage.setResizable(false);
	    primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
