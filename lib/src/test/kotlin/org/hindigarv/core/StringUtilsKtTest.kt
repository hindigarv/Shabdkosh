package org.hindigarv.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StringUtilsKtTest {

    @Test
    fun shouldSplitTextToWords() {
        val text = "बिहार कॉन्ग्रेस में टूट की खबरों ने जोर पकड़ लिया है। 2017 में जब नीतीश कुमार ने महागठबंधन छोड़ा था, तब भी पार्टी में टूट हुई थी।"
        val words = listOf("बिहार", "कॉन्ग्रेस", "में", "टूट", "की", "खबरों", "ने", "जोर", "पकड़", "लिया", "है", "2017", "में",
            "जब", "नीतीश", "कुमार", "ने", "महागठबंधन", "छोड़ा", "था", "तब", "भी", "पार्टी", "में", "टूट", "हुई", "थी")
        assertEquals(words, text.tokenize())
    }

    @Test
    fun shouldIgnoreMoreSpaces() {
        val text = "सब सो  गए  क्या"
        val words = listOf("सब", "सो", "गए", "क्या")
        assertEquals(words, text.tokenize())
    }

    @Test
    fun shouldIgnoreTabAndNewLines() {
        val text = "सब सो \t गए\nक्या"
        val words = listOf("सब", "सो", "गए", "क्या")
        assertEquals(words, text.tokenize())
    }

    @Test
    fun shouldIgnoreCommaAndFullStops() {
        val text = "सब, सो गए क्या."
        val words = listOf("सब", "सो", "गए", "क्या")
        assertEquals(words, text.tokenize())
    }
    @Test
    fun shouldIgnoreHindiFullStops() {
        val text = "सब, सो गए क्या।"
        val words = listOf("सब", "सो", "गए", "क्या")
        assertEquals(words, text.tokenize())
    }

    @Test
    fun shouldIgnoreQuestionMarks() {
        val text = "सब सो गए क्या?"
        val words = listOf("सब", "सो", "गए", "क्या")
        assertEquals(words, text.tokenize())
    }

    @Test
    fun shouldIgnoreExclamationMark() {
        val text = "सब सो गए क्या!"
        val words = listOf("सब", "सो", "गए", "क्या")
        assertEquals(words, text.tokenize())
    }

    @Test
    fun shouldSplitWordsWithOnlyDot() {
        val text = "सब.सो"
        val words = listOf("सब", "सो")
        assertEquals(words, text.tokenize())
    }

    @Test
    fun shouldSplitWordsWithManyDots() {
        val text = "सब....सो"
        val words = listOf("सब", "सो")
        assertEquals(words, text.tokenize())
    }

    @Test
    fun shouldSplitWordsWithOnlyComma() {
        val text = "सब,सो"
        val words = listOf("सब", "सो")
        assertEquals(words, text.tokenize())
    }

    @Test
    fun shouldSplitWordsWithManyCommas() {
        assertThat("सब,,,,,,,सो".tokenize()).isEqualTo(listOf("सब", "सो"))
    }

    @Test
    fun shouldSplitWordsWithManyCommasAndDotsMixed() {
        assertThat("सब,,.,,.,..,,.सो".tokenize()).isEqualTo(listOf("सब", "सो"))
    }

    @Test
    fun shouldIgnorePercentageSign() {
        val text = "पतंजलि का प्रॉफिट 100% चैरिटी के लिये।"
        val words = listOf("पतंजलि", "का", "प्रॉफिट", "100", "चैरिटी", "के", "लिये")
        assertEquals(words, text.tokenize())
    }

    @Test
    fun shouldIgnoreBraces() {
        val text = "पतंजलि (का) प्रॉफिट 100% [चैरिटी] {के} लिये।"
        val words = listOf("पतंजलि", "का", "प्रॉफिट", "100", "चैरिटी", "के", "लिये")
        assertEquals(words, text.tokenize())
    }

    @Test
    internal fun shouldHandleStarAsASeparator() {
        val words = "अगर कोई आपकी उम्मीद से जीता हैं, तो आप भी उसके यकीन पर खरा उतरिये......... *क्योंकि*इंसान उसि से उम्मीद रखता है, जिसको वो अपने सबसे करीब मानता है!..... #जय श्रीराम \uD83D\uDEA9\uD83D\uDEA9".tokenize()
        assertThat(words).contains("इंसान")
    }

    @Test
    internal fun shouldSplitWordByNBSP() {
        val words = "माघ बिहू इस साल 2021,  15 जनवरी, शुक्रवार को मनाया जाएगा । असम के लोग इस त्योहार के साथ ही नये साल की शुरुआत मानते हैं । #३ह".tokenize()
        assertThat(words).contains("शुरुआत")
    }

    @Test
    internal fun shouldSplitWordsByUnderscore() {
        val words = """सभी लोग बढ़चढ़कर हिस्सा लीजिये।।
 अंतराष्ट्रीय मुहिम  बनाने में सहयोग की अपेक्षा⚔️

#देवनिंदा_कानून_बनाओ
#देवनिंदा_कानून_बनाओ
#देवनिंदा_कानून_बनाओ

Copy &amp; Rt✌️""".tokenize()
        assertThat(words).contains("कानून")
    }

    @Test
    internal fun shouldSplitWordByDash() {
        val words = """मशहूर होने का शौक हमें नहीं है साहब बस सुबह-सुबह कोई जय जय श्री राम बोल दे तो छाती चौड़ी हो जाती है" ...!

            🙏 " जय श्री राम " 🙏""".tokenize()
        assertThat(words).contains("सुबह")
    }

    @Test
    internal fun shouldSplitWordByEmoji() {
        val words = """मित्रों कितने चाहते हो कि मैं ट्विटर पर एक्टिव रहूं और
कितने चाहते है कि छोड़ दूं 😞

जवाब🙏""".tokenize()
        assertThat(words).contains("जवाब")
    }
    @Test
    internal fun shouldHandleOneSidedQuotes() {
        val text = """अजी सुनते हो, एक राज की बात बताती हूं..??

मोहब्बत के लिए "दिल और दिमाग" दोनों चाहिए..??Flushed faceFlushed face"""
        val words = text.tokenize()
        assertThat(words).contains("दिल")
        assertThat(words).contains("दिमाग")
    }

    @Test
    internal fun shouldHandleOneSidedItalicQuotes() {
        val words = """“करोगे जब अपनी मातृभाषा का मान सम्मान
तभी बढ़ेगी देश की शान”

हिंदू-राष्ट्र हिंदी भाषा के बिना अधूरा है
गर्व से हिंदी का प्रयोग करे
हर एक सनातनी की पहचान है हमारी हिंदी

#विश्व_हिंदी_दिवस 🚩

https://t.co/UgTnxnuWwp""".tokenize()
        assertThat(words).contains("शान")
    }

    @Test
    internal fun shouldFindWordsSeparatedByQuote() {
        val text = """जो भाग्य बनाता है महादेव कहलाता है''
जो पार करे नैया वो पार्वती मईया...

"मोहब्बत"लिबास" नहीं
       जो हर "रोज़"बदला जाए,
मोहब्बत"कफ़न"हैं
      "पहन"कर"उतारा"नहीं जाता..✍"""
        val words = text.tokenize()
        assertThat(words).contains("मोहब्बत")
        assertThat(words).contains("लिबास")
        assertThat(words).contains("कफ़न")
    }

    @Test
    internal fun shouldSplitWordsByInvertedDoubleQuotes() {
        val words = "“इज़्ज़त तो सबको ही चाहिए \nलेकिन लोग बस वापस देना भूल जाते हैं”".tokenize()
        assertThat(words).contains("इज़्ज़त")
    }
    @Test
    internal fun shouldFindWordWithVisarga() {
        val words = "सबको ही चाहिए लेकिन: लोग बस भूल जाते हैं”".tokenize()
        assertThat(words).contains("लेकिन")
    }

    @Test
    internal fun shouldFindWordWithSemiColon() {
        val words = "सबको ही चाहिए लेकिन; लोग बस भूल जाते हैं”".tokenize()
        assertThat(words).contains("लेकिन")
    }

    @Test
    fun removeNukta() {
    }
}