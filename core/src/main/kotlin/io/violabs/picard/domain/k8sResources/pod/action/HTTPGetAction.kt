package io.violabs.picard.domain.k8sResources.pod.action

data class HTTPGetAction(
    val portNum: Int? = null,
    val portString: String? = null,
    val host: String? = null,
    val httpHeaders: List<HttpHeader>? = null,
    val path: String? = null,
    val scheme: String? = null
) {
    val port: Int =
        portNum ?: portString?.toInt()
        ?: throw IllegalArgumentException("port must be set")
}