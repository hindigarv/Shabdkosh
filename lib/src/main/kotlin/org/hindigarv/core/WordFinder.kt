package org.hindigarv.core

import mu.KotlinLogging
import org.hindigarv.core.model.Word
import java.net.URL
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.scheduleAtFixedRate


fun String.split(): List<String> {
    return this.split(",")
        .map { it.trim() }
        .filter { it.isNotBlank() }
}

class WordFinder(autoRefresh: Boolean = false, private val regexEnabled: Boolean = false) {

    private val logger = KotlinLogging.logger {}
    private var dictionary: Map<String, Word> = emptyMap()
    private val url =
        "https://docs.google.com/spreadsheets/d/e/2PACX-1vTnYyZxqwSjM3IPG9TchbZcAUDNM_Y4zbZCFjimzQKVjQpNNinNRj4CeWzXaHDNcDEJ_EPOrtBLycRD/pub?gid=0&single=true&output=tsv"

    init {
        refresh()
        if (autoRefresh) {
            Timer("refresher", true).scheduleAtFixedRate(
                TimeUnit.MINUTES.toMillis(5),
                TimeUnit.MINUTES.toMillis(5)
            ) { refresh() }
        }
    }


    private fun refresh() {
        dictionary = URL(url).readText()
            .split("\r\n")
            .map { fromCSVLine(it) }
            .filter { it.isPresent }
            .map { it.get() }
            .flatMap { word -> word.roops.map { it to word } }
            .toMap()
        logger.info { "Dictionary refreshed with ${dictionary.size} words" }
    }

    private fun fromCSVLine(line: String): Optional<Word> {
        // Header line is => ""शब्द\tमूल\tपर्याय\tमूल भाषा में\tरूप"")

        val columns = line.split("\t")
        if (columns.size < 3) {
            return Optional.empty()
        }
        val shabd = columns[0].trim().removeNukta()
        val mool = columns[1].trim()
        val paryays = columns[2].split()

        if ("शब्द" == shabd || shabd.isEmpty() || mool.isEmpty() || paryays.isEmpty()) {
            return Optional.empty()
        }

        val moolRoop: String = columns.getOrElse(3) { "" }.trim()
        var roops = columns.getOrElse(4) { "" }
            .removeNukta()
            .split()
            .plus(shabd) // The shabd itself is one roop of it
            .distinct()
        val regex = columns.getOrElse(5) { "" }.removeNukta().trim()
        if (regexEnabled && regex.isNotBlank()) {
            val generatedRoops = RegexWordsGenerator(Regex(regex)).generate()
            roops = roops.toMutableList()
            roops.addAll(generatedRoops)
            roops = roops.distinct()
        }
        return Optional.of(Word(shabd, mool, paryays, moolRoop, roops, regex))
    }


    fun find(text: String): List<Word> {
        return text.removeNukta()
            .tokenize()
            .mapNotNull(dictionary::get)
            .distinct()
    }

}