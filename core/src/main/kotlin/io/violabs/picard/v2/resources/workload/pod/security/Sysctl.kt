package io.violabs.picard.v2.resources.workload.pod.security

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(withListGroup = true)
data class Sysctl(
    /**
     * Name of a property to set
     */
    val name: String,
    /**
     * Value of a property to set
     */
    val value: String
)