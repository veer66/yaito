package net.veerkesto

class UnkLinkBuilder() : LinkBuilder {
    override fun build(context: LinkContext): Link? {
        if (context.bestLink != null) {
            return null
        }

        val source = context.path[context.leftBoundary];

        return Link(s = context.leftBoundary,
                    linkType = LinkType.UNK,
                    wordCount = source.wordCount + 1,
                    unkCount = source.unkCount + 1)
    }
}