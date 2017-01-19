package net.veerkesto

fun buildPath(dix: PrefixTree<Boolean>, linkBuilders: Array<LinkBuilder>, text: String): Array<Link?> {

    var context = LinkContext(text = text,
            i = 0,
            ch = '!',
            path = kotlin.arrayOfNulls<Link>(text.length + 1),
            leftBoundary = 0,
            bestLink = null)

    context.path[0] = Link(0, LinkType.INIT, 0, 0)
    for ((i, ch) in text.withIndex()) {
        context.bestLink = null
        context.i = i
        context.ch = ch
        linkBuilders.forEach { linkBuilder ->
            val link = linkBuilder.build(context)
            if (link != null) {
                if (link.isBetterThan(context.bestLink)) {
                    context.bestLink = link
                }
            }
        }

        if (context.bestLink == null) {
            throw RuntimeException("Cannot find link")
        }

        context.path[i + 1] = context.bestLink!!

        if (context.bestLink?.linkType != LinkType.UNK) {
            context.leftBoundary = i + 1
        }
    }
    return context.path
}
