package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.domain.k8sResources.Quantity

data class Device(
    val name: String,
    val basic: Basic? = null
) {
    data class Basic(
        val attributes: Map<String, Attribute>? = null,
        val capacity: Map<String, Quantity>? = null
    )

    data class Attribute(
        val bool: Boolean? = null,
        val int: Long? = null,
        val string: String? = null,
        val version: String? = null
    )
}