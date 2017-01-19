package net.veerkesto

data class DixPointer(var nodeId:Int, val s: Int, var offset: Int, var isFinal: Boolean, var valid: Boolean)

class DixLinkBuilder(val dix: PrefixTree<Boolean>) : LinkBuilder {
    var max = 0xFF
    var pointers: Array<DixPointer?> = kotlin.arrayOfNulls<DixPointer>(max)
    var n = 0

    fun updatePointer(pointer: DixPointer?, ch:Char) {
        val childNode = dix.lookup(pointer!!.nodeId, pointer!!.offset, ch)
        if (childNode != null) {
            pointer!!.nodeId = childNode.childId
            pointer!!.offset++
            pointer!!.isFinal = childNode.isFinal
        } else {
            pointer!!.valid = false
        }
    }

    override fun build(context: LinkContext): Link? {
        pointers[n] = DixPointer(0, context.i, 0, false, true)

        n++

        if (n == max) {
            var newPointers: Array<DixPointer?> = kotlin.arrayOfNulls<DixPointer>(max * 2)
            for ((i, pointer) in pointers.withIndex()) run {
                newPointers[i] = pointer
            }
            pointers = newPointers
        }

        var i = 0
        var j = 0
        var selectedLink: Link? = null

        while (i < n) {
            updatePointer(pointers[i], context.ch)
            val pointer = pointers[i]
            if (pointer!!.valid) {
                if (j < i) {
                    pointers[j] = pointer
                }
                j++

                if (pointer.isFinal) {
                    val s = pointer.s
                    val source = context.path[s]
                    var link: Link? = Link(s, LinkType.DICT, source!!.wordCount + 1, source!!.unkCount)
                    if (link?.isBetterThan(selectedLink)!!) {
                        selectedLink = link
                    }
                }
            }
            i++
        }
        n = j

        return selectedLink
    }

}
