package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.domain.k8sResources.workload.pod.action.ExecAction
import io.violabs.picard.domain.k8sResources.workload.pod.action.HTTPGetAction
import io.violabs.picard.domain.k8sResources.workload.pod.action.SleepAction
import io.violabs.picard.domain.k8sResources.workload.pod.action.TCPSocketAction

data class LifecycleHandler(
    val exec: ExecAction? = null,
    val httpGet: HTTPGetAction? = null,
    val sleep: SleepAction? = null,
    val tcpSocket: TCPSocketAction? = null
)