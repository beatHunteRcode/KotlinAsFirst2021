package lesson5.task1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class TestsST {
    @Test
    fun averageStockPriceTest() {
        Assertions.assertEquals(
            mapOf("GAZPROM" to 10.0, "YANDEX" to 5.0),
            lesson5.task1.averageStockPrice(listOf("GAZPROM" to 10.0, "YANDEX" to 5.0))
        )
        Assertions.assertEquals(
            mapOf("OK" to 100.0, "VK" to 234.0, " " to 0.0),
            lesson5.task1.averageStockPrice(listOf("OK" to 100.0, "VK" to 234.0, " " to 0.0))
        )
        Assertions.assertEquals(
            mapOf<String, Double>(),
            lesson5.task1.averageStockPrice(listOf())
        )
    }

    @Test
    fun bagPackingTest() {
        Assertions.assertEquals(
            setOf("Банка вкусной тушеной говядины"),
            bagPacking(
                mapOf("Шпроты" to (175 to 500), "Банка вкусной тушеной говядины" to (50 to 800)),
                200
            )
        )
        Assertions.assertEquals(
            emptySet<String>(),
            bagPacking(
                mapOf("Танковый аккумулятор" to (5000 to 20000)),
                1000
            )
        )
        Assertions.assertEquals(
            setOf("Аптечка \"Salewa\"", "РГД-5", "Шлем \"Ратник\" 6Б47", "Активные наушники \"ГСШ-10\""),
            bagPacking(
                mapOf(  "РГД-5" to (100 to 1000), "Шлем \"Ратник\" 6Б47" to (700 to 4000),
                        "Аптечка \"Salewa\"" to (400 to 1200), "Активные наушники \"ГСШ-10\"" to (1000 to 2000)),
                3000
            )
        )
    }

    @Test
    fun findSumOfTwoTest() {
        assertEquals(
            Pair(2, 6),
            findSumOfTwo(listOf(56, 34, 25, 77, 45, 213, 75, 23, 5, 90), 100)
        )
        assertEquals(
            Pair(0, 4),
            findSumOfTwo(listOf(5, 9, 4, 7, 5), 10)
        )
        assertEquals(
            Pair(-1, -1),
            findSumOfTwo(listOf(21, 78, 5, 46, 23), 45)
        )
    }

}
