#!/home/kmiura/java/jdk1.8.0_121/bin/jjs

var StringClass = java.lang.String.class
var s = 'Javascript'.substring(0, 4)
print(s.getClass()) // 出力結果: class java.lang.String
print(StringClass.cast(s.getClass()))　// 出力結果: java.lang.ClassCastException: Cannot cast java.lang.Class to java.lang.String /* Class型をString型にキャストしようとしているため、ClassCastExceptionがスローされる。*/
