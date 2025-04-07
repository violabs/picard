package io.violabs.picard.domain

data class KeyToPath(
    val key: String,
    val path: String,
    val mode: Int? = null
)