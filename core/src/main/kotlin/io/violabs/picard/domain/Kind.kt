package io.violabs.picard.domain

enum class Kind {
    DEPLOYMENT,
    JOB,
    KUBELET_CONFIGURATION,
    POD,
    SERVICE;

    override fun toString(): String = name
        .split("_")
        .joinToString("") {
            it.lowercase().replaceFirstChar(Char::uppercase)
        }
}