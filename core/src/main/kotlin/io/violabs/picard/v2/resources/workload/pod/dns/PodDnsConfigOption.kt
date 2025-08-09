package io.violabs.picard.v2.resources.workload.pod.dns

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * PodDNSConfigOption defines DNS resolver options of a pod.
 */
@GeneratedDsl(withListGroup = true)
data class PodDnsConfigOption(
    /**
     * Name is this DNS resolver option's name. Required.
     */
    val name: String,
    /**
     * Value is this DNS resolver option's value.
     */
    val value: String? = null
)