package net.veerkesto

fun makeDix(sortedWords: Array<String>): PrefixTree<Boolean> {
    val wordsWithPayload = sortedWords.map { word -> Pair(word, true) }.toTypedArray()
    return PrefixTree(wordsWithPayload)
}
