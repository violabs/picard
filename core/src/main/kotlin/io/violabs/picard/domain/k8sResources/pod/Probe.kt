package io.violabs.picard.domain.k8sResources.pod

data class Probe(
    val exec: ExecAction? = null,
    val httpGet: HTTPGetAction? = null,
    val tcpSocket: TCPSocketAction? = null,
    val initialDelaySeconds: Int? = null,
    val terminationGracePeriodSeconds: Long? = null,
    val periodSeconds: Int? = null,
    val timeoutSeconds: Int? = null,
    val successThreshold: Int? = null,
    val failureThreshold: Int? = null,
    val grpcAction: GRPCAction? = null
)