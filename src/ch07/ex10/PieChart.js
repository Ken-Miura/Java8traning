// run as "jjs -fx  -- file_path"

/* JavaよりもJavascriptで書くほうが難しい。
　* 出てくる型の種類が多いと、（Javascriptだと変数宣言時にvarしか書かないので）型がわかりづらくなって書きにくくなる。
　*/

if (arguments.length!=1) {
	print("Enter the path to data file.");
	exit(0);
}

var Files = java.nio.file.Files;
var File = java.io.File;
var StandardCharsets = java.nio.charset.StandardCharsets;
var PieChart = javafx.scene.chart.PieChart;
var Data = javafx.scene.chart.PieChart.Data;
var ArrayList = java.util.ArrayList;
var FXCollections = javafx.collections.FXCollections

var lines = Files.readAllLines(new File(arguments[0]).toPath(), StandardCharsets.UTF_8);
var dataList = new ArrayList(lines.size());
for (var i=0; i<lines.size(); i++) {
	var nameAndNum = lines[i].split(/\s/);
	var dataForChart = new Data(nameAndNum[0], nameAndNum[1]);
	dataList.add(dataForChart);
}
var pieChartData = FXCollections.observableArrayList(dataList);
var chart = new PieChart(pieChartData);
chart.setTitle("Test Chart");
$STAGE.title = "Pie Chart Test";
$STAGE.scene = new javafx.scene.Scene(chart);
