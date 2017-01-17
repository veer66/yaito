package net.veerkesto

import java.util.*

data class TextRange(val s:Int, val e:Int)

fun pathToRanges(path: Array<Link>): Array<TextRange> {
    if (path.isEmpty()) {
        return arrayOf()
    }

    if (path.count() == 1) {
        return arrayOf(TextRange(0, path.count() - 1))
    }

    var e = path.count() - 1
    var ranges = ArrayList<TextRange>()

    while (path[e].linkType != LinkType.INIT) {
        val s = path[e].s
        ranges.add(TextRange(s, e))
        e = s
    }

    ranges.reverse()
    return ranges.toTypedArray()
}

fun rangesToTokens(text: String, ranges: Array<TextRange>): Array<String> =
        ranges.map{range -> text.substring(range.s, range.e)}.toTypedArray()
