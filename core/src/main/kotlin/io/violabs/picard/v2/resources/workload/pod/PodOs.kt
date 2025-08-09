package io.violabs.picard.v2.resources.workload.pod

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * PodOS defines the OS parameters of a pod.
 */
@GeneratedDsl
data class PodOs(
    /**
     * Name is the name of the operating system.
     * The currently supported values are linux and windows.
     * Additional value may be defined in future and can be one of:
     * https://github.com/opencontainers/runtime-spec/blob/master/config.md#platform-specific-configuration
     * Clients should expect to handle additional values and treat
     * unrecognized values in this field as os: null
     */
    val name: String
)