package org.hindigarv.core

class RegexWordsGenerator(regex: Regex) {
    private val pattern = regex.pattern
    private var index: Int = 0

    init {
        require(pattern.startsWith("^")) {
            "Regex must start with '^'"
        }
        require(pattern.endsWith("$")) {
            "Regex must end with '$'"
        }
    }

    fun hasNext(): Boolean {
        if (pattern[index] == '$' || pattern[index+1] == '$') return false
        return true
    }

    fun generate(): List<String> {
        var words = mutableListOf("")
        while (pattern[index] != '$') {
            val ch = pattern[index]
            index++

            if (ch == '^') continue
            if (ch == '(') {
                val options = getNextOptions()
                if (options.isNotEmpty()) {
                    words = words.flatMap { r -> options.map { o -> r + o } }.toMutableList()
                }
                continue
            }
            words = words.map { r -> r + ch }.toMutableList()
        }
        return words
    }

    private fun getNextOptions(): List<String> {
        val options = mutableListOf<String>()
        var currOption = ""
        while (pattern[index] != ')') {
            val ch = pattern[index]
            index++
            if (ch == '|') {
                options.add(currOption)
                currOption = ""
                continue
            }
            currOption += ch;
        }
        options.add(currOption)
        index++
        if (pattern[index] == '?') {
            options.add("")
            index++
        }
        return options
    }

}