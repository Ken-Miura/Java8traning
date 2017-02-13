#!/home/kmiura/java/jdk1.8.0_121/bin/jjs

// jjsに打ち込んだコマンドは以下の通り（jdk1.8.0_121に付属するjjsではテキストの問題のような奇妙な表示は起こらなかった。）
var b = new java.math.BigInteger('1234567890987654321')
print(b)
print(b.mod(java.math.BigInteger.TEN))

// 結果得られた出力
// 1234567890987654321 // bに対する出力
// 1 // b.mod(java.math.BigInteger.TEN)に対する出力
