/**
 * Copyright 2017 Ken Mirua
 */
package ch05.ex12;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * タスクと時間が記載されているファイルを読み込み、読み込んだ時間の1時間前にそのタスクの通知を行う。
 * タスクと時間を記載するファイルは、UTF-8でエンコーディングする。タスクと時間を記載するファイルは"タスク	時間"を１行とする形式で記載する。記載例は以下の通り。
 * 
 * task1	2017-01-15T13:21:50.874+09:00[Asia/Tokyo]
 * task2	2017-01-17T05:36:41.865+01:00[CET]
 * task3	2017-01-20T20:00:41.866-08:00[America/Los_Angeles]
 */
public final class NotifyTask extends Application {

	private static final Timer timer = new Timer(true);
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parameters params = getParameters();
		List<String> args = params.getRaw();
		if (args.size() != 1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error");
			alert.setContentText("通知したいタスクが記載されているファイルのパスを入力してください。");
			alert.showAndWait();
			System.exit(0);
		}
		
		Platform.setImplicitExit(false);
			
		List<String> lines = Files.readAllLines(new File(args.get(0)).toPath(), StandardCharsets.UTF_8);
		Set<Task> taskSet = new HashSet<>();
		for (final String line: lines) {
			String[] taskAndTime = line.split("[\\s]+");
			taskSet.add(new Task(taskAndTime[0], ZonedDateTime.parse(taskAndTime[1])));
		}
		
		final CountDownLatch latch = new CountDownLatch(taskSet.size());
		
		taskSet.stream().forEach(task->{
			ZonedDateTime localTime = task.getTime().toInstant().atZone(ZoneId.systemDefault());
			ZonedDateTime notificationTime = localTime.minusHours(1);
			timer.schedule(new TimerTask() {
					
				@Override
				public void run() {
					Platform.runLater(()->{
				        Alert notification = new Alert(AlertType.INFORMATION);
				        notification.setTitle("notification");
				        notification.getDialogPane().setHeaderText(task.getName());
				        notification.getDialogPane().setContentText("One hour before " + task.getName());	
				        notification.showAndWait();
				        latch.countDown();
					});
				}
			}, Date.from(notificationTime.toInstant()));
		});
		
		new Thread (()->{
			try {
				latch.await();
			} catch (InterruptedException ignored) {
				ignored.printStackTrace();
			}
			Platform.exit();
		}).start();
	}
}
