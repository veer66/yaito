package net.veerkesto

class LatinLinkBuilder:
        PatLinkBuilder({ (it >= 'A' && it <= 'Z') || (it >= 'a' && it <= 'z')},
                       LinkType.LATIN)