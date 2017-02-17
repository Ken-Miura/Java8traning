//[jjs上で試したAPI　>　java.lang.String]
var s = new java.lang.String("test")
print(s.length())

//[Javaでテストプログラムを書くより、jjsで試したほうが簡単かどうか]
//JavaでAPIの動作確認をするためのプログラムは以下のようになり、jjsでAPIの確認をした場合と比較して明らかにタイプ量が多い。
//よってJavaでテストプログラムを書くより、jjsで試したほうが簡単である。
//public class Foo {
//	public static void main (String[] args) {
//		String s = new java.lang.String("test");
//		System.out.println(s.length());
//	}
//}
