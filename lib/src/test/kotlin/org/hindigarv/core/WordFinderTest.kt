package org.hindigarv.core

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

import org.junit.jupiter.api.Assertions.*

internal class WordFinderTest {

    private val wordFinder = WordFinder()

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