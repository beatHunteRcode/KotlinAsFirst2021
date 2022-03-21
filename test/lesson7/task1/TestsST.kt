package lesson7.task1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File

class TestsST {

    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        Assertions.assertEquals(expectedContent, content)
    }

    @Test
    fun deleteMarkedTest() {
        deleteMarked("input/TestsST/deleteMarked_1.txt", "temp.txt")
        assertFileContent(
            "temp.txt",
            """
а это нормас пусть остается
пробел ниже

-на первый вгляд кажется что это нужно удалить но нет
sample_text
-о нет..................... ............... ............ good ending __""".trimIndent()
        )
        File("temp.txt").delete()
    }

    @Test
    fun chooseLongestChaoticWord() {
        lesson7.task1.chooseLongestChaoticWord("input/TestsST/chooseLongestChaoticWord.txt", "temp.txt")
        assertFileContent(
            "temp.txt",
            """дЕВЯТИЧОК, дЕСяТиЧоК""".trimIndent()
        )
        File("temp.txt").delete()
    }


    @Test
    fun markdownToHtmlSimple() {
        markdownToHtmlSimple("input/TestsST/markdownToHtmlSimple.md", "temp.html")
        checkHtmlSimpleExample()
    }

    private fun checkHtmlSimpleExample() {
        val result = File("temp.html").readText().replace(Regex("[\\s\\n\\t]"), "")
        val expected =
            """<html><body><p>История Найт-Сити <b>началась</b> с мечты одного человека — предпринимателя Ричарда Найта. 
</p><p>В конце XX века 
</p><p>Найт задумал построить <s>город будущего</s>, свободный от старых <i>предрассудков</i> и <b>не ограниченный</b> бессмысленными запретами.

</p></body></html>""".trimIndent().replace(Regex("[\\s\\n\\t]"), "")
        Assertions.assertEquals(expected, result)

        File("temp.html").delete()
    }

}