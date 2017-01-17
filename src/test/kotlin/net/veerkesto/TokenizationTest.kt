package net.veerkesto

import org.junit.Test
import org.junit.Assert

class PrefixTreeTest {
    @Test
    fun basicTokenize() {
        var words = arrayOf("ก", "กข", "ขคง")
        words.sort()
        val yaito = Yaito(words)
        Assert.assertArrayEquals(arrayOf("ก", "ขคง"), yaito.tokenize("กขคง"))
    }

    @Test
    fun fiveWordsTokenize() {
        var words = arrayOf("ก", "กข", "ขคง", "ค", "ง")
        words.sort()
        val yaito = Yaito(words)
        Assert.assertArrayEquals(arrayOf("กข", "ค"), yaito.tokenize("กขค"))
    }

    @Test
    fun spaceTokenizer() {
        var words = arrayOf("ก")
        val yaito = Yaito(words)
        Assert.assertArrayEquals(arrayOf("รร", " ", "มม"), yaito.tokenize("รร มม"))
    }

    @Test
    fun latinTokenizer() {
        var words = arrayOf("ก")
        val yaito = Yaito(words)
        Assert.assertArrayEquals(arrayOf("รร", "AB", "มม"), yaito.tokenize("รรABมม"))
    }

}