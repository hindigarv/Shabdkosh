package org.hindigarv.core

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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

    @Test
    fun shouldGenerateWordWithoutOptionWhenQuestionMarkIsUsed() {
        val regex = Regex("^अ(a|b)?ल(्)?\$")
        val roops = listOf("अल", "अaल", "अbल", "अल्", "अaल्", "अbल्")
        val generated = RegexWordsGenerator(regex).generate()
        assertTrue(generated.size == roops.size)
        assertTrue(generated.containsAll(roops))
    }

    @Test
    fun testAmbulance() {
        val regex = Regex("^ए(म्|ं)ब(्य)?ुले(न्|ं)स(्)?\$")
        val roops = listOf("एम्ब्युलेन्स्", "एम्ब्युलेन्स", "एम्ब्युलेंस्", "एम्ब्युलेंस", "एम्बुलेन्स्", "एम्बुलेन्स", "एम्बुलेंस्", "एम्बुलेंस", "एंब्युलेन्स्", "एंब्युलेन्स", "एंब्युलेंस्", "एंब्युलेंस", "एंबुलेन्स्", "एंबुलेन्स", "एंबुलेंस्", "एंबुलेंस")
        val generated = RegexWordsGenerator(regex).generate()
        assertEquals(roops.size, generated.size)
        assertTrue(generated.containsAll(roops))
    }


}