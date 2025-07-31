package io.violabs.picard.v2.resources.service.ingress.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * IngressPortStatus represents the error condition of a service port
 */
@GeneratedDsl(withListGroup = true)
data class IngressPortStatus(
    /**
     * port is the port number of the ingress port.
     */
    val port: Int,
    /**
     * protocol is the protocol of the ingress port. The supported values are: "TCP", "UDP", "SCTP"
     */
    val protocol: String,
    /**
     * error is to record the problem with the service port The format of the error shall comply with
     * the following rules: - built-in error values shall be specified in this file and those shall
     * use CamelCase names
     *
     * cloud provider specific error values must have names that comply with the format foo.example.com/CamelCase.
     */
    val error: String? = null
)