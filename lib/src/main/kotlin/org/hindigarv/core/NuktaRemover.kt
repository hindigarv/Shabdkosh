package org.hindigarv.core

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
