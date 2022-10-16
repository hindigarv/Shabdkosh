package org.hindigarv.core

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RegexTest {

    @Test
    fun shouldGenerateWordsWithOneSetOfOptions() {
        val regex = Regex("^अ(क्|क्क|क)ल\$")
        val roops = listOf("अक्ल", "अक्कल", "अकल")
        assertEquals(roops, RegexWordsGenerator(regex).generate())
    }

    @Test
    fun shouldGenerateWordsWithOneSetOfOptionsAtEnd() {
        val regex = Regex("^अ(क्|क्क|क)\$")
        val roops = listOf("अक्", "अक्क", "अक")
        assertEquals(roops, RegexWordsGenerator(regex).generate())
    }

    @Test
    fun shouldGenerateWordsWithOneSetOfOptionsAtStartAndEnd() {
        val regex = Regex("^(क्|क्क|क)\$")
        val roops = listOf("क्", "क्क", "क")
        assertEquals(roops, RegexWordsGenerator(regex).generate())
    }

    @Test
    fun shouldGenerateWordsWithoutCaretAndDollarSign() {
        val regex = Regex("(क्|क्क|क)")
        Assertions.assertThrows(java.lang.IllegalArgumentException::class.java) { RegexWordsGenerator(regex) }
    }

    @Test
    fun shouldGenerateWordsWithThreeSetOfOptions() {
        val regex = Regex("^अ(क्|क्क|क)लम(ं|न्)द(ि|ी)\$")
        val roops = listOf(
            "अक्लमंदि",
            "अक्लमंदी",
            "अक्लमन्दि",
            "अक्लमन्दी",
            "अक्कलमंदि",
            "अक्कलमंदी",
            "अक्कलमन्दि",
            "अक्कलमन्दी",
            "अकलमंदि",
            "अकलमंदी",
            "अकलमन्दि",
            "अकलमन्दी",
        )
        assertEquals(roops, RegexWordsGenerator(regex).generate())
    }


}