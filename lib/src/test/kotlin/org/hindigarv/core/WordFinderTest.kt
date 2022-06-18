package org.hindigarv.core

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

import org.junit.jupiter.api.Assertions.*

internal class WordFinderTest {

    private val wordFinder = WordFinder()

//    @Test
//    fun shouldSplitTextToWords() {
//        val text = "बिहार कॉन्ग्रेस में टूट की खबरों ने जोर पकड़ लिया है। 2017 में जब नीतीश कुमार ने महागठबंधन छोड़ा था, तब भी पार्टी में टूट हुई थी।"
//        val words = listOf("बिहार", "कॉन्ग्रेस", "में", "टूट", "की", "खबरों", "ने", "जोर", "पकड़", "लिया", "है", "2017", "में",
//            "जब", "नीतीश", "कुमार", "ने", "महागठबंधन", "छोड़ा", "था", "तब", "भी", "पार्टी", "में", "टूट", "हुई", "थी")
//        assertEquals(words, wordFinder.toWords(text))
//    }
//
//    @Test
//    fun shouldIgnoreMoreSpaces() {
//        val text = "सब सो  गए  क्या"
//        val words = listOf("सब", "सो", "गए", "क्या")
//        assertEquals(words, wordFinder.toWords(text))
//    }
//
//    @Test
//    fun shouldIgnoreTabAndNewLines() {
//        val text = "सब सो \t गए\nक्या"
//        val words = listOf("सब", "सो", "गए", "क्या")
//        assertEquals(words, wordFinder.toWords(text))
//    }
//
//    @Test
//    fun shouldIgnoreCommaAndFullStops() {
//        val text = "सब, सो गए क्या."
//        val words = listOf("सब", "सो", "गए", "क्या")
//        assertEquals(words, wordFinder.toWords(text))
//    }
//    @Test
//    fun shouldIgnoreHindiFullStops() {
//        val text = "सब, सो गए क्या।"
//        val words = listOf("सब", "सो", "गए", "क्या")
//        assertEquals(words, wordFinder.toWords(text))
//    }
//
//    @Test
//    fun shouldIgnoreQuestionMarks() {
//        val text = "सब सो गए क्या?"
//        val words = listOf("सब", "सो", "गए", "क्या")
//        assertEquals(words, wordFinder.toWords(text))
//    }
//
//    @Test
//    fun shouldIgnoreExclamationMark() {
//        val text = "सब सो गए क्या!"
//        val words = listOf("सब", "सो", "गए", "क्या")
//        assertEquals(words, wordFinder.toWords(text))
//    }
//
//    @Test
//    fun shouldSplitWordsWithOnlyDot() {
//        val text = "सब.सो"
//        val words = listOf("सब", "सो")
//        assertEquals(words, wordFinder.toWords(text))
//    }
//
//    @Test
//    fun shouldSplitWordsWithManyDots() {
//        val text = "सब....सो"
//        val words = listOf("सब", "सो")
//        assertEquals(words, wordFinder.toWords(text))
//    }
//
//    @Test
//    fun shouldSplitWordsWithOnlyComma() {
//        val text = "सब,सो"
//        val words = listOf("सब", "सो")
//        assertEquals(words, wordFinder.toWords(text))
//    }
//
//    @Test
//    fun shouldSplitWordsWithManyCommas() {
//        assertThat(wordFinder.toWords("सब,,,,,,,सो")).isEqualTo(listOf("सब", "सो"))
//    }
//
//    @Test
//    fun shouldSplitWordsWithManyCommasAndDotsMixed() {
//        assertThat(wordFinder.toWords("सब,,.,,.,..,,.सो")).isEqualTo(listOf("सब", "सो"))
//    }
//
//    @Test
//    fun shouldIgnorePercentageSign() {
//        val text = "पतंजलि का प्रॉफिट 100% चैरिटी के लिये।"
//        val words = listOf("पतंजलि", "का", "प्रॉफिट", "100", "चैरिटी", "के", "लिये")
//        assertEquals(words, wordFinder.toWords(text))
//    }
//
//    @Test
//    fun shouldIgnoreBraces() {
//        val text = "पतंजलि (का) प्रॉफिट 100% [चैरिटी] {के} लिये।"
//        val words = listOf("पतंजलि", "का", "प्रॉफिट", "100", "चैरिटी", "के", "लिये")
//        assertEquals(words, wordFinder.toWords(text))
//    }
//
//    @Test
//    internal fun shouldHandleStarAsASeparator() {
//        val words = wordFinder.toWords("अगर कोई आपकी उम्मीद से जीता हैं, तो आप भी उसके यकीन पर खरा उतरिये......... *क्योंकि*इंसान उसि से उम्मीद रखता है, जिसको वो अपने सबसे करीब मानता है!..... #जय श्रीराम \uD83D\uDEA9\uD83D\uDEA9")
//        assertThat(words).contains("इंसान")
//    }
//
//    @Test
//    internal fun shouldSplitWordByNBSP() {
//        val words = wordFinder.toWords("माघ बिहू इस साल 2021,  15 जनवरी, शुक्रवार को मनाया जाएगा । असम के लोग इस त्योहार के साथ ही नये साल की शुरुआत मानते हैं । #३ह")
//        assertThat(words).contains("शुरुआत")
//    }
//
//    @Test
//    internal fun shouldSplitWordsByUnderscore() {
//        val words = wordFinder.toWords("""सभी लोग बढ़चढ़कर हिस्सा लीजिये।।
// अंतराष्ट्रीय मुहिम  बनाने में सहयोग की अपेक्षा⚔️
//
//#देवनिंदा_कानून_बनाओ
//#देवनिंदा_कानून_बनाओ
//#देवनिंदा_कानून_बनाओ
//
//Copy &amp; Rt✌️""")
//        assertThat(words).contains("कानून")
//    }
//
//    @Test
//    internal fun shouldSplitWordByDash() {
//        val words = wordFinder.toWords("""मशहूर होने का शौक हमें नहीं है साहब बस सुबह-सुबह कोई जय जय श्री राम बोल दे तो छाती चौड़ी हो जाती है" ...!
//
//            🙏 " जय श्री राम " 🙏""")
//        assertThat(words).contains("सुबह")
//    }
//
//    @Test
//    internal fun shouldSplitWordByEmoji() {
//        val words = wordFinder.toWords("""मित्रों कितने चाहते हो कि मैं ट्विटर पर एक्टिव रहूं और
//कितने चाहते है कि छोड़ दूं 😞
//
//जवाब🙏""")
//        assertThat(words).contains("जवाब")
//    }
//    @Test
//    internal fun shouldHandleOneSidedQuotes() {
//        val text = """अजी सुनते हो, एक राज की बात बताती हूं..??
//
//मोहब्बत के लिए "दिल और दिमाग" दोनों चाहिए..??Flushed faceFlushed face"""
//        val words = wordFinder.toWords(text)
//        assertThat(words).contains("दिल")
//        assertThat(words).contains("दिमाग")
//    }
//
//    @Test
//    internal fun shouldHandleOneSidedItalicQuotes() {
//        val words = wordFinder.toWords("""“करोगे जब अपनी मातृभाषा का मान सम्मान
//तभी बढ़ेगी देश की शान”
//
//हिंदू-राष्ट्र हिंदी भाषा के बिना अधूरा है
//गर्व से हिंदी का प्रयोग करे
//हर एक सनातनी की पहचान है हमारी हिंदी
//
//#विश्व_हिंदी_दिवस 🚩
//
//https://t.co/UgTnxnuWwp""")
//        assertThat(words).contains("शान")
//    }
//
//    @Test
//    internal fun shouldFindWordsSeparatedByQuote() {
//        val text = """जो भाग्य बनाता है महादेव कहलाता है''
//जो पार करे नैया वो पार्वती मईया...
//
//"मोहब्बत"लिबास" नहीं
//       जो हर "रोज़"बदला जाए,
//मोहब्बत"कफ़न"हैं
//      "पहन"कर"उतारा"नहीं जाता..✍"""
//        val words = wordFinder.toWords(text)
//        assertThat(words).contains("मोहब्बत")
//        assertThat(words).contains("लिबास")
//        assertThat(words).contains("कफ़न")
//    }
//
//    @Test
//    internal fun shouldSplitWordsByInvertedDoubleQuotes() {
//        val words = wordFinder.toWords("“इज़्ज़त तो सबको ही चाहिए \nलेकिन लोग बस वापस देना भूल जाते हैं”")
//        assertThat(words).contains("इज़्ज़त")
//    }
//    @Test
//    internal fun shouldFindWordWithVisarga() {
//        val words = wordFinder.toWords("सबको ही चाहिए लेकिन: लोग बस भूल जाते हैं”")
//        assertThat(words).contains("लेकिन")
//    }
//
//    @Test
//    internal fun shouldFindWordWithSemiColon() {
//        val words = wordFinder.toWords("सबको ही चाहिए लेकिन; लोग बस भूल जाते हैं”")
//        assertThat(words).contains("लेकिन")
//    }

    @Test
    fun shouldFindForeignWords() {
        val text = "गजब है पटाके बेन करवाने वाला लक्ष्मी पूजा का मुहूर्त बता रहा है......"
        val words = wordFinder.find(text)
        assertEquals(1, words.size)
        assertEquals("गजब", words[0].shabd)
    }

    @Test
    fun shouldFindMultipleForeignWords() {
        val text = """@narendramodi
 जी अगर सुभाष चंद्रबोस जी, ने अगर अंग्रेजो के सामने हथियार डाल दिए होते तो हम आजाद नहीं होते लेकिन आपने अर्बन नक्सल के सामने सरेंडर कर दिया हैं!इन जैसे लोगो को अच्छे से सबक सिखाओ नहीं तो देश को आपको माफ़ नहीं करेगा,JNU को सुधारो"""
        val words = wordFinder.find(text)
        assertEquals(5, words.size)
        assertEquals("अगर", words[0].shabd)
        assertEquals("आजाद", words[1].shabd)
        assertEquals("लेकिन", words[2].shabd)
        assertEquals("सरेंडर", words[3].shabd)
        assertEquals("सबक", words[4].shabd)
    }

    @Test
    fun shouldFindWordWithNukta() {
        val text = """@narendramodi
 जी अगर सुभाष चंद्रबोस जी, ने अगर अंग्रेजो के सामने हथियार डाल दिए होते तो हम आज़ाद नहीं होते लेकिन आपने अर्बन नक्सल के सामने सरेंडर कर दिया हैं!इन जैसे लोगो को अच्छे से सबक सिखाओ नहीं तो देश को आपको माफ़ नहीं करेगा,JNU को सुधारो"""
        val words = wordFinder.find(text)
        assertEquals(5, words.size)
        assertEquals("अगर", words[0].shabd)
        assertEquals("आजाद", words[1].shabd)
        assertEquals("लेकिन", words[2].shabd)
        assertEquals("सरेंडर", words[3].shabd)
        assertEquals("सबक", words[4].shabd)
    }

    @Test
    fun shouldNotConsiderSameWordWithNuktaAsADifferentWord() {
        val text = """@narendramodi
 जी अगर सुभाष चंद्रबोस जी, ने अगर अंग्रेजो के सामने हथियार डाल दिए होते तो हम आजाद नहीं होते लेकिन आपने अर्बन नक्सल के सामने सरेंडर कर दिया हैं! आज़ाद . इन जैसे लोगो को अच्छे से सबक सिखाओ नहीं तो देश को आपको माफ़ नहीं करेगा,JNU को सुधारो"""
        val words = wordFinder.find(text)
        assertEquals(5, words.size)
        assertEquals("अगर", words[0].shabd)
        assertEquals("आजाद", words[1].shabd)
        assertEquals("लेकिन", words[2].shabd)
        assertEquals("सरेंडर", words[3].shabd)
        assertEquals("सबक", words[4].shabd)
    }

    @Test
    internal fun shouldIgnoreRepeatedWords() {
        val text = "aaa लेकिन aaa आजाद bbb लेकिन ccc"
        val words = wordFinder.find(text)
        assertThat(words).hasSize(2)
        assertThat(words[0].shabd).isEqualTo("लेकिन")
        assertThat(words[1].shabd).isEqualTo("आजाद")
    }

    @Test
    internal fun shouldFindSingleQuotedWords() {
        val text = "aaa 'लेकिन' aaa 'आजाद' bbb"
        val words = wordFinder.find(text)
        assertThat(words).hasSize(2)
        assertThat(words[0].shabd).isEqualTo("लेकिन")
        assertThat(words[1].shabd).isEqualTo("आजाद")
    }

    @Test
    internal fun shouldFindSingleInvertedQuotedWords() {
        val words = wordFinder.find("aaa ‘लेकिन’ bbb")
        assertThat(words).hasSize(1)
        assertThat(words[0].shabd).isEqualTo("लेकिन")
    }

    @Test
    internal fun shouldFindDoubleQuotedWords() {
        val text = "aaa \"लेकिन\" aaa \"आजाद\" bbb"
        val words = wordFinder.find(text)
        assertThat(words).hasSize(2)
        assertThat(words[0].shabd).isEqualTo("लेकिन")
        assertThat(words[1].shabd).isEqualTo("आजाद")
    }

    @Test
    internal fun shouldHashTaggedWords() {
        val text = "aaa #लेकिन aaa #आजाद bbb"
        val words = wordFinder.find(text)
        assertThat(words).hasSize(2)
        assertThat(words[0].shabd).isEqualTo("लेकिन")
        assertThat(words[1].shabd).isEqualTo("आजाद")
    }



    @Test
    internal fun shouldFindDoubleQuotedSingleQuotedAndHashTaggedWordsFromOneText() {
        val text = "aaa \"लेकिन\" aaa #आजाद bbb 'अगर' ccc "
        val words = wordFinder.find(text)
        assertThat(words).hasSize(3)
        assertThat(words[0].shabd).isEqualTo("लेकिन")
        assertThat(words[1].shabd).isEqualTo("आजाद")
        assertThat(words[2].shabd).isEqualTo("अगर")
    }


}