package io.violabs.picard.v2.resources.workload.pod.dns

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * PodDNSConfig defines the DNS parameters of a pod in addition to those generated from DNSPolicy.
 */
@GeneratedDsl
data class PodDnsConfig(
    /**
     * A list of DNS name server IP addresses. This will be appended to the base nameservers generated from DNSPolicy. Duplicated nameservers will be removed.
     *
     * Atomic: will be replaced during a merge
     */
    val nameservers: List<String>? = null,
    /**
     * A list of DNS resolver options. This will be merged with the base options generated from DNSPolicy. Duplicated entries will be removed. Resolution options given in Options will override those that appear in the base DNSPolicy.
     *
     * Atomic: will be replaced during a merge
     */
    val options: List<PodDnsConfigOption>? = null,
    /**
     * A list of DNS search domains for host-name lookup. This will be appended to the base search paths generated from DNSPolicy. Duplicated search paths will be removed.
     *
     * Atomic: will be replaced during a merge
     */
    val searches: List<String>? = null
)

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