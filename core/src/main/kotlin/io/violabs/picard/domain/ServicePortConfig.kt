package io.violabs.picard.domain

data class ServicePortConfig(
    val protocol: Protocol,
    val port: Int,
    val targetPort: Int
) {
    enum class Protocol {
        TCP,
        UDP,
        SCTP
    }
}