package org.hindigarv.core

import com.vdurmont.emoji.EmojiParser

val devanagariChars =
        "ऀँंःऄअआइईउऊऋऌऍऎएऐऑऒओऔकखगघङचछजझञटठडढणतथदधनऩपफबभमयरऱलळऴवशषसहऺऻ़ऽािीुूृॄॅॆेैॉॊोौ्ॎॏॐ॒॑॓॔ॕॖॗक़ख़ग़ज़ड़ढ़फ़य़ॠॡॢॣॱॲॳॴॵॶॷॸॹॺॻॼॽॾॿ"


fun String.tokenize(): List<String> {
    return EmojiParser.removeAllEmojis(this)
        .split(Regex("[^${devanagariChars}]"))
        .filter { it.isNotBlank() }
}

fun String.removeNukta(): String {
    return this.replace('क़', 'क')
        .replace('ख़', 'ख')
        .replace('ग़', 'ग')
        .replace('ज़', 'ज')
        .replace('ड़', 'ड')
        .replace('ढ़', 'ढ')
        .replace('फ़', 'फ')
        .replace("़", "")
}
