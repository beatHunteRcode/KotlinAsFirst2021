package lesson6.task1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class TestsST {

    @Test
    fun plusMinusTest() {
        Assertions.assertEquals(451, plusMinus("1000 - 300 + 543 - 900 + 108"))
        Assertions.assertEquals(5, plusMinus("5"))
        Assertions.assertEquals(0, plusMinus("10 - 10"))
        Assertions.assertThrows(IllegalArgumentException::class.java) { plusMinus("10+2+4-5") }
        Assertions.assertThrows(IllegalArgumentException::class.java) { plusMinus("-7") }
        Assertions.assertThrows(IllegalArgumentException::class.java) { plusMinus("") }
        Assertions.assertThrows(IllegalArgumentException::class.java) { plusMinus("-2 - 2 + 4") }
    }

    @Test
    fun bestLongJump() {
        Assertions.assertEquals(-1, bestLongJump("- - - - - -"))
        Assertions.assertEquals(-1, bestLongJump("% % %"))
        Assertions.assertEquals(-1, bestLongJump(""))
        Assertions.assertEquals(-1, bestLongJump("            "))
        Assertions.assertEquals(776, bestLongJump("234 453 776"))
        Assertions.assertEquals(33335, bestLongJump("33335"))
    }
}