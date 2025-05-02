package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.domain.DSLBuilder

data class CrossVersionObjectReference(
    val kind: String,
    val name: String,
    val apiVersion: String? = null
) {
    class Builder : DSLBuilder<CrossVersionObjectReference> {
        var kind: String? = null
        var name: String? = null
        var apiVersion: String? = null

        override fun build(): CrossVersionObjectReference {
            return CrossVersionObjectReference(
                kind = requireNotNull(kind),
                name = requireNotNull(name),
                apiVersion = apiVersion
            )
        }
    }
}