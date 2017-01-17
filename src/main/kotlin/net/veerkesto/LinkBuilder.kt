package net.veerkesto

interface LinkBuilder {
    fun build(context: LinkContext): Link?
}
