package io.violabs.picard.domain.k8sResources.workload.pod

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
)