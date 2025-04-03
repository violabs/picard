package io.violabs.picard.domain

interface K8sEnum {
    val name: String
    fun properCase(): String = name
        .split("_")
        .joinToString("") {
            it.lowercase().replaceFirstChar(Char::uppercase)
        }
}