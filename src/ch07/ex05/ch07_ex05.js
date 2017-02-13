#!/home/kmiura/java/jdk1.8.0_121/bin/jjs

var ArrayList = java.util.ArrayList

function listWithLogging() { var arr = new (Java.extend(ArrayList)) { add: function(x) { print('Adding ' + x); return Java.super(arr).add(x); }  }; return arr; }
// 以下listWithLoggingの動作確認(ログを記録可能なArrayListを複数作成できることを確認)
var list1 = listWithLogging()
list1.add('Javascript')
var list2 = listWithLogging()
list2.add('Java')
