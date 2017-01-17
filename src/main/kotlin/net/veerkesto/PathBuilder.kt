package net.veerkesto

fun buildPath(dix: PrefixTree<Boolean>, linkBuilders: Array<LinkBuilder>, text: String): Array<Link> {
    var path = Array(text.length + 1, { i -> Link(0, LinkType.INIT, 0, 0) })
    var leftBoundary = 0

    for ((i, ch) in text.withIndex()) {
        var bestLink: Link? = null

        linkBuilders.forEach { linkBuilder ->
            val context = LinkContext(text = text,
                                      i = i,
                                      ch = ch,
                                      path = path,
                                      leftBoundary = leftBoundary,
                                      bestLink = bestLink)
            val link = linkBuilder.build(context)
            if (link != null) {
                if (link.isBetterThan(bestLink)) {
                    bestLink = link
                }
            }
        }

        if (bestLink == null) {
            throw RuntimeException("Cannot find link")
        }

        path[i + 1] = bestLink!!

        if (bestLink?.linkType != LinkType.UNK) {
            leftBoundary = i + 1
        }
    }
    return path
}
