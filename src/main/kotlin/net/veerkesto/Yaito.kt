package net.veerkesto

class Yaito(words: Array<String>) {
    val dix = makeDix(words)
    val linkBuilderFactories = arrayOf({ DixLinkBuilder(dix) },
            { LatinLinkBuilder()},
            { SpaceLinkBuilder()},
            { UnkLinkBuilder()})

    fun tokenize(text: String): Array<String> {
        val linkBuilders = linkBuilderFactories.map { it() }.toTypedArray()
        val path = buildPath(dix, linkBuilders, text)
        val ranges = pathToRanges(path)
        return rangesToTokens(text, ranges)
    }
}