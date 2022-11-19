package org.hindigarv.core

import com.vdurmont.emoji.EmojiParser

fun String.tokenize(): List<String> {
    return EmojiParser.removeAllEmojis(this)
        .split(Regex("[\\s\\-\"\\[\\],। .?!#_*%”“'‘’:;|~^&+=(){}<>…॰०१२३४५६७८९0123456789a-zA-Z]"))
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
