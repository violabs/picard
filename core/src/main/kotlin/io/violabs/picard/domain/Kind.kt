package io.violabs.picard.domain

enum class Kind {
    JOB,
    KUBELET_CONFIGURATION,
    POD;

    override fun toString(): String = name
        .split("_")
        .joinToString("") {
            it.lowercase().replaceFirstChar(Char::uppercase)
        }
}