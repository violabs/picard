package io.violabs.picard.v2.resources.workload.pod.container.action

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.IntOrString

/**
 * TCPSocketAction describes an action based on opening a socket
 */
@GeneratedDsl
data class TcpSocketAction(
    /**
     * Number or name of the port to access on the container.
     * Number must be in the range 1 to 65535.
     * Name must be an IANA_SVC_NAME.
     */
    val port: IntOrString,
    /**
     * Optional: Host name to connect to, defaults to the pod IP.
     */
    val host: String? = null
)