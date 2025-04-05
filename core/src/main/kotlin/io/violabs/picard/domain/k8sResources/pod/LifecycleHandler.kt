package io.violabs.picard.domain.k8sResources.pod

data class LifecycleHandler(
    val exec: ExecAction? = null,
    val httpGet: HTTPGetAction? = null,
    val sleep: SleepAction? = null,
    val tcpSocket: TCPSocketAction? = null
)