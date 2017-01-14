/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex12;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public final class NotifyTask extends Application {

	private static final Timer timer = new Timer(true);
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		final double rem = new Text("").getLayoutBounds().getHeight();
		
		GridPane root = new GridPane();
		root.setPadding(new Insets(0.5 * rem));
		root.setHgap(0.8 * rem);
		root.setVgap(0.8 * rem);
		
		Label explanationLabel = new Label("Set the task.");
		root.add(explanationLabel, 0, 0);
		
		Label monthLabel = new Label("month: ");
		root.add(monthLabel, 0, 1);
		Spinner<Integer> monthSpinner = new Spinner<>(1, 12, 0);
		root.add(monthSpinner, 0, 2);
		
		Label dateLabel = new Label("date: ");
		root.add(dateLabel, 1, 1);
		Spinner<Integer> dateSpinner = new Spinner<>(1, 31, 0);
		root.add(dateSpinner, 1, 2);
		
		Label hourLabel = new Label("hour: ");
		root.add(hourLabel, 2, 1);
		Spinner<Integer> hourSpinner = new Spinner<>(0, 23, 0);
		root.add(hourSpinner, 2, 2);
		
		Label minuteLabel = new Label("minute: ");
		root.add(minuteLabel, 3, 1);		
		Spinner<Integer> minuteSpinner = new Spinner<>(0, 59, 0);
		root.add(minuteSpinner, 3, 2);
		
		Label timezoneLabel = new Label("time zone: ");
		root.add(timezoneLabel, 4, 1);
		Set<String> zoneIds = ZoneId.getAvailableZoneIds();
		ComboBox<String> timeZoneCombo = new ComboBox<>();
		for (final String s: zoneIds) {
			timeZoneCombo.getItems().add(s);
		}
		root.add(timeZoneCombo, 4, 2);
		
		Label taskNameLabel = new Label("task name: ");
		root.add(taskNameLabel, 5, 1);
		TextField taskNameTextField = new TextField();
		root.add(taskNameTextField, 5, 2);
		
		Button button = new Button("Set");
		GridPane.setHalignment(button, HPos.RIGHT);
		root.add(button, 5, 3);
		
		button.setOnAction(e->{
			ZonedDateTime taskTime = ZonedDateTime.of(ZonedDateTime.now().getYear(), monthSpinner.getValue(), dateSpinner.getValue(), hourSpinner.getValue(), minuteSpinner.getValue(), 0, 0, ZoneId.of(timeZoneCombo.getValue()));
			ZonedDateTime localTaskTime = taskTime.toInstant().atZone(ZoneId.systemDefault());
			ZonedDateTime notificationTime = localTaskTime.minusHours(1);
			String taskName = taskNameTextField.getText();
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					Platform.runLater(()->{
				        Alert notification = new Alert(AlertType.INFORMATION);
				        notification.setTitle("notification");
				        notification.getDialogPane().setHeaderText(taskName);
				        notification.getDialogPane().setContentText("One hour before " + taskName);	
				        notification.show();
					});
				}
			}, Date.from(notificationTime.toInstant()));
	        
			Alert setTaskCompletion = new Alert(AlertType.INFORMATION);
	        setTaskCompletion.setTitle("completed setting task");
	        setTaskCompletion.getDialogPane().setHeaderText("completed setting task: "+ taskName);
	        setTaskCompletion.getDialogPane().setContentText("You will be notified at "+ localTaskTime);	
	        setTaskCompletion.show();
		});
		
		Scene scene = new Scene(root);
		
	    primaryStage.setTitle(NotifyTask.class.getSimpleName());
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

}
