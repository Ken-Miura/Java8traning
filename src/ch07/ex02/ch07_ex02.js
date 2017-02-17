// インタラクティブな取り組みは、通常のワークフローと比較すると、streamの中間操作から終端操作までを一文で書けないので手間に感じた。
var String = java.lang.String
var Files = java.nio.file.Files
var Paths = java.nio.file.Paths
var Arrays = java.util.Arrays
var System = java.lang.System
var StandardCharsets = java.nio.charset.StandardCharsets
var workingDir = System.getProperty('user.dir')
var inputFile = workingDir + java.io.File.separator + 'alice.txt'
var contents = new String(Files.readAllBytes(Paths.get(inputFile)), StandardCharsets.UTF_8)
var wordStream = Arrays.asList(contents.split(/\W+/)).stream()
var filteredStream = wordStream.filter(function(s) s.length()>12)
var distinctStream = filteredStream.distinct()
var sortedStream = distinctStream.sorted()
sortedStream.forEach(function(s) print(s))
