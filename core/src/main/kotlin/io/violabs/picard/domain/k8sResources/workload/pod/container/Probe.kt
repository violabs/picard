package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.k8sResources.workload.pod.action.ExecAction
import io.violabs.picard.domain.k8sResources.workload.pod.action.GRPCAction
import io.violabs.picard.domain.k8sResources.workload.pod.action.HTTPGetAction
import io.violabs.picard.domain.k8sResources.workload.pod.action.TCPSocketAction

data class Probe(
    val exec: ExecAction? = null,
    val httpGet: HTTPGetAction? = null,
    val tcpSocket: TCPSocketAction? = null,
    val grpcAction: GRPCAction? = null,
    val initialDelaySeconds: Int? = null,
    val terminationGracePeriodSeconds: Long? = null,
    val periodSeconds: Int? = null,
    val timeoutSeconds: Int? = null,
    val successThreshold: Int? = null,
    val failureThreshold: Int? = null
) {
    class Builder : DSLBuilder<Probe> {
        private var exec: ExecAction? = null
        private var httpGet: HTTPGetAction? = null
        private var tcpSocket: TCPSocketAction? = null
        private var grpcAction: GRPCAction? = null
        var initialDelaySeconds: Int? = null
        var terminationGracePeriodSeconds: Long? = null
        var periodSeconds: Int? = null
        var timeoutSeconds: Int? = null
        var successThreshold: Int? = null
        var failureThreshold: Int? = null

        fun exec(init: ExecAction.Builder.() -> Unit) {
            exec = ExecAction.Builder().apply(init).build()
        }

        fun httpGet(init: HTTPGetAction.Builder.() -> Unit) {
            httpGet = HTTPGetAction.Builder().apply(init).build()
        }

        fun tcpSocket(init: TCPSocketAction.Builder.() -> Unit) {
            tcpSocket = TCPSocketAction.Builder().apply(init).build()
        }

        fun grpcAction(init: GRPCAction.Builder.() -> Unit) {
            grpcAction = GRPCAction.Builder().apply(init).build()
        }

        override fun build(): Probe {
            return Probe(
                exec,
                httpGet,
                tcpSocket,
                grpcAction,
                initialDelaySeconds,
                terminationGracePeriodSeconds,
                periodSeconds,
                timeoutSeconds,
                successThreshold,
                failureThreshold
            )
        }
    }
}