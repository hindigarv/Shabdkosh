package org.hindigarv.core


val devanagariChars =
        "ऀँंःऄअआइईउऊऋऌऍऎएऐऑऒओऔकखगघङचछजझञटठडढणतथदधनऩपफबभमयरऱलळऴवशषसहऺऻ़ऽािीुूृॄॅॆेैॉॊोौ्ॎॏॐ॒॑॓॔ॕॖॗक़ख़ग़ज़ड़ढ़फ़य़ॠॡॢॣॱॲॳॴॵॶॷॸॹॺॻॼॽॾॿ"


fun String.tokenize(): List<String> = this
    .split(Regex("[^${devanagariChars}]"))
    .filter { it.isNotBlank() }

fun String.removeNukta(): String = this.replace('क़', 'क')
    .replace('ख़', 'ख')
    .replace('ग़', 'ग')
    .replace('ज़', 'ज')
    .replace('ड़', 'ड')
    .replace('ढ़', 'ढ')
    .replace('फ़', 'फ')
    .replace("़", "")
