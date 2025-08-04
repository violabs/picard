package io.violabs.picard.v2.resources.workload.pod.container.action

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * HTTPHeader describes a custom header to be used in HTTP probes
 */
@GeneratedDsl(withListGroup = true)
data class HttpHeader(
    /**
     * The header field name.
     * This will be canonicalized upon output, so case-variant names will be understood as the same header.
     */
    val name: String,
    /**
     * The header field value
     */
    val value: String
)