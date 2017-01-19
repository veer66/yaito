package net.veerkesto

open class PatLinkBuilder(val isAcceptable: (Char) -> Boolean, val linkType: LinkType) : LinkBuilder {
    var s: Int? = null
    var e: Int? = null

    override fun build(context: LinkContext): Link? {
        if (s == null) {
            if (isAcceptable(context.ch)) {
                s = context.i
            }
        }

        if (s != null) {
            if (isAcceptable(context.ch)) {
                if (context.text.count() == context.i + 1 || !isAcceptable(context.text[context.i + 1])) {
                    e = context.i
                }
            } else {
                s = null
                e = null
            }
        }

        if (s != null && e != null) {
            val source = context.path[s!!]
            return Link(s = s!!,
                    linkType = linkType,
                    wordCount = source!!.wordCount + 1,
                    unkCount = source!!.unkCount)
        }

        return null
    }}
