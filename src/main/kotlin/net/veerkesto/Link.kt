package net.veerkesto

enum class LinkType {
    INIT,
    DICT,
    UNK,
    LATIN,
    SPACE
}

data class Link(val s: Int, val linkType: LinkType, val wordCount:Int, val unkCount:Int) {
    fun isBetterThan(another: Link?): Boolean {
        if (another == null) {
            return true
        }

        if (another.unkCount > unkCount) {
            return true
        }

        if (another.unkCount < unkCount) {
            return false
        }

        if (another.wordCount > wordCount) {
            return true
        }

        if (another.wordCount < wordCount) {
            return false
        }

        if (another.linkType == LinkType.UNK && linkType != LinkType.UNK) {
            return true
        }

        return false
    }

}
