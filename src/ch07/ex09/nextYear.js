// run as "jjs -scripting"
var text = "Next year, you wil be ";
if (arguments.length!=0) {
	print(text + (Number(arguments[0])+1));
} else if (typeof $ENV.AGE !== "undefined") {
	print(text + (Number($ENV.AGE)+1));
} else {
	var age = readLine("Your age: ");
	print(text + (Number(age)+1));
}
