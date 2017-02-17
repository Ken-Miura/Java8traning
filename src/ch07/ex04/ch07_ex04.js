var StringClass = java.lang.String.class
var s = 'Javascript'.substring(0, 4)
print(s.getClass()) // 出力結果: class java.lang.String
print(StringClass.cast(s)) // 特定のバージョン以下のjjsでは問題が発生するらしいが、jre1.8.0_102に含まれるjjsではすでにバグ修正済みらしく、問題は発生しない。
