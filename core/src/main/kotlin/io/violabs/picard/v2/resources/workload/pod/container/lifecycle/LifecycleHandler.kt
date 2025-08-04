package io.violabs.picard.v2.resources.workload.pod.container.lifecycle

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.pod.container.action.SleepAction
import io.violabs.picard.v2.resources.workload.pod.container.action.TcpSocketAction
import io.violabs.picard.v2.resources.workload.pod.container.action.ExecAction
import io.violabs.picard.v2.resources.workload.pod.container.action.HttpGetAction

/**
 * LifecycleHandler defines a specific action that should be taken in a lifecycle hook.
 * One and only one of the fields, except TCPSocket must be specified.
 */
@GeneratedDsl
data class LifecycleHandler(
    /**
     * Exec specifies the action to take.
     */
    val exec: ExecAction? = null,
    /**
     * HTTPGet specifies the http request to perform.
     */
    val httpGet: HttpGetAction? = null,
    /**
     * Sleep represents the duration that the container should sleep before being terminated.
     */
    val sleep: SleepAction? = null,
    /**
     * Deprecated. TCPSocket is NOT supported as a LifecycleHandler and kept for the backward compatibility.
     * There are no validation of this field and lifecycle hooks will fail in runtime when tcp handler is specified.
     */
    val tcpSocket: TcpSocketAction? = null
)