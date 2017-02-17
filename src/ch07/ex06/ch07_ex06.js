// run as "jjs -scripting"

function pipe () { for (var i = 0; i < arguments.length; i++) {	$EXEC(arguments[i], $OUT) } }
pipe('find .', 'grep src.zip', 'sort') // 以下動作確認
print($OUT)
