package io.violabs.picard.domain.k8sResources.pod.action

data class TCPSocketAction(
    val portNum: Int? = null,
    val portString: String? = null,
    val host: String? = null
) {
    val port: Int =
        portNum ?: portString?.toInt()
        ?: throw IllegalArgumentException("port must be set")
}