package io.violabs.picard.v2.resources.workload.pod.container

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ContainerPort represents a network port in a single container.
 */
@GeneratedDsl(withListGroup = true)
data class ContainerPort(
    /**
     * Number of port to expose on the pod's IP address. This must be a valid port number, 0 < x < 65536.
     */
    val containerPort: Int,
    /**
     * What host IP to bind the external port to.
     */
    val hostIP: String? = null,
    /**
     * Number of port to expose on the host. If specified, this must be a valid port number, 0 < x < 65536. If HostNetwork is specified, this must match ContainerPort. Most containers do not need this.
     */
    val hostPort: Int? = null,
    /**
     * If specified, this must be an IANA_SVC_NAME and unique within the pod. Each named port in a pod must have a unique name. Name for the port that can be referred to by services.
     */
    val name: String? = null,
    /**
     * Protocol for port. Must be UDP, TCP, or SCTP. Defaults to "TCP".
     */
    val protocol: String? = null
)