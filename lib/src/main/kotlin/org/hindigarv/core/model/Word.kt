package org.hindigarv.core.model

data class Word(
    val shabd: String,
    val mool: String,
    val paryays: List<String>,
    val moolRoop: String,
    val roops: List<String>,
    val regex: String?
)