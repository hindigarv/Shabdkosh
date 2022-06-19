package org.hindigarv.core

import com.vdurmont.emoji.EmojiParser
import org.hindigarv.core.model.Word
import java.net.URL
import java.util.Optional


fun String.split(): List<String> {
    return this.split(",")
        .map { it.trim() }
        .filter { it.isNotBlank() }
}

class WordFinder() {

    private var dictionary: Map<String, Word> = emptyMap()
    init {
        val url = "https://docs.google.com/spreadsheets/d/e/2PACX-1vTnYyZxqwSjM3IPG9TchbZcAUDNM_Y4zbZCFjimzQKVjQpNNinNRj4CeWzXaHDNcDEJ_EPOrtBLycRD/pub?gid=0&single=true&output=csv"
        val csvContent = URL(url).readText()
        dictionary = csvContent.split("\r\n")
            .map { fromCSVLine(it) }
            .filter { it.isPresent }
            .map { it.get() }
            .flatMap { word -> word.roops.map { it to word } }
            .toMap()
    }

    private fun fromCSVLine(line: String): Optional<Word> {
        val parts = line.split(",")
        if (parts.size < 3) {
            return Optional.empty()
        }
        val shabd = parts[0].trim().removeNukta()
        val mool = parts[1].trim()
        val paryays = parts[2].split()

        if ("शब्द" == shabd || shabd.isEmpty() || mool.isEmpty() || paryays.isEmpty()) {
            return Optional.empty()
        }

        val moolRoop: String = parts.getOrElse(3) { "" }.trim()
        val roops = parts.getOrElse(4) { "" }
            .removeNukta()
            .split()
            .plus(shabd) // The shabd itself is one roop of it
            .distinct()
        val regex = parts.getOrElse(5) { "" }.trim()
        return Optional.of(Word(shabd, mool, paryays, moolRoop, roops, regex))
    }


    fun find(text: String): List<Word> {
        return text.removeNukta()
            .tokenize()
            .mapNotNull(dictionary::get)
            .distinct()
    }

}