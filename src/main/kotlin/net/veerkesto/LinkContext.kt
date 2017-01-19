package net.veerkesto

data class LinkContext (
        val text: String,
        var i: Int,
        var ch: Char,
        val path: Array<Link?>,
        var leftBoundary: Int,
        var bestLink: Link?)
