package io.violabs.picard.domain.k8sResources.pod

import io.violabs.picard.domain.k8sResources.pod.action.ExecAction
import io.violabs.picard.domain.k8sResources.pod.action.HTTPGetAction
import io.violabs.picard.domain.k8sResources.pod.action.SleepAction
import io.violabs.picard.domain.k8sResources.pod.action.TCPSocketAction

data class LifecycleHandler(
    val exec: ExecAction? = null,
    val httpGet: HTTPGetAction? = null,
    val sleep: SleepAction? = null,
    val tcpSocket: TCPSocketAction? = null
)