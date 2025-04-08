package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.domain.DslBuilder
import io.violabs.picard.domain.k8sResources.workload.pod.action.ExecAction
import io.violabs.picard.domain.k8sResources.workload.pod.action.GRPCAction
import io.violabs.picard.domain.k8sResources.workload.pod.action.HTTPGetAction
import io.violabs.picard.domain.k8sResources.workload.pod.action.TCPSocketAction

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
) {
    class Builder : DslBuilder<Probe> {
        private var exec: ExecAction? = null
        private var httpGet: HTTPGetAction? = null
        private var tcpSocket: TCPSocketAction? = null
        private var initialDelaySeconds: Int? = null
        private var terminationGracePeriodSeconds: Long? = null
        private var periodSeconds: Int? = null
        private var timeoutSeconds: Int? = null
        private var successThreshold: Int? = null
        private var failureThreshold: Int? = null
        private var grpcAction: GRPCAction? = null

        override fun build(): Probe {
            return Probe(
                exec,
                httpGet,
                tcpSocket,
                initialDelaySeconds,
                terminationGracePeriodSeconds,
                periodSeconds,
                timeoutSeconds,
                successThreshold,
                failureThreshold,
                grpcAction
            )
        }
    }
}