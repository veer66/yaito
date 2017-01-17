package net.veerkesto

data class DixPointer(val nodeId:Int, val s: Int, val offset: Int, val isFinal: Boolean)

class DixLinkBuilder(val dix: PrefixTree<Boolean>) : LinkBuilder {

    var pointers: Array<DixPointer> = arrayOf()

    fun updatePointer(pointer: DixPointer, ch:Char) : DixPointer? {
        val childNode = dix.lookup(pointer.nodeId, pointer.offset, ch) ?: return null
        return DixPointer(nodeId = childNode.childId,
                          s = pointer.s,
                          offset = pointer.offset + 1,
                          isFinal = childNode.isFinal)
    }

    override fun build(context: LinkContext): Link? {
        pointers = pointers.plus(DixPointer(0, context.i, 0, false))
                .map {pointer -> updatePointer(pointer, context.ch)}
                .filterNotNull()
                .toTypedArray()

        var selectedLink: Link? = null
        for (finalPointer in pointers.filter {pointer -> pointer.isFinal}) {
            val source = context.path[finalPointer.s]
            var link: Link? = Link(finalPointer.s, LinkType.DICT, source.wordCount + 1, source.unkCount)

            if (link?.isBetterThan(selectedLink)!!) {
                selectedLink = link
            }

        }
        return selectedLink
    }

}
