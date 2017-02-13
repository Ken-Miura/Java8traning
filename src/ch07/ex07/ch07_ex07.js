#!/home/kmiura/java/jdk1.8.0_121/bin/jjs
// 未完成。
var ProcessBuilder = java.lang.ProcessBuilder;
var InputStreamReader = java.io.InputStreamReader
var BufferedReader = java.io.BufferedReader
var OutputStreamWriter = java.io.OutputStreamWriter
var BufferedWriter = java.io.BufferedWriter

function pipe () {
 var ProcessArray = Java.type('java.lang.Process[]');
 var processes = new ProcessArray(arguments.length);
 for (var i = 0; i < arguments.length; i++) {
	var command = arguments[i].split(/\s/);
	var pb = new ProcessBuilder(command).redirectErrorStream(true);
	processes[i] = pb.start();
 }
 for (var j = 0; j < arguments.length-1; j++) {
	var reader = new BufferedReader(new InputStreamReader(processes[j].getInputStream(), 'UTF-8'));
	var writer = new BufferedWriter(new OutputStreamWriter(processes[j+1].getOutputStream(), 'UTF-8'));
	var line;
	while((line=reader.readLine())!=null) {
		writer.write(line, 0, line.length);
		writer.newLine();
		writer.flush();
	}
 }
 var resultReader =  new BufferedReader(new InputStreamReader(processes[arguments.length-1].getInputStream(), 'UTF-8'));
 var result;	
 while((result=resultReader.readLine())!=null) {
	print(result);
 }
}

pipe('find .', 'grep src.zip', 'sort') // 以下動作確認
print($OUT)

