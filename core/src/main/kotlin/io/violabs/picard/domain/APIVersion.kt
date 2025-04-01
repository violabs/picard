package io.violabs.picard.domain

interface APIVersion {
    val name: String
    val ref: String?
    fun refString(): String = ref ?: name.lowercase()
}