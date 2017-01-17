package net.veerkesto

data class LinkContext (
    val text: String,
    val i: Int,
    val ch: Char,
    val path: Array<Link>,
    val leftBoundary: Int,
    val bestLink: Link?)
