#!C:\Program Files\Java\jre1.8.0_102\bin\jrunscript.exe -f
var text = "Next year, you wil be ";
if (arguments!=0) {
	print(text + arguments[0]);
} else if (typeof $ENV.AGE !== "undefined") {
	print(text + $ENV.AGE);
} else {
	var age = readLine("Your age: ");
	print(text + age);
}