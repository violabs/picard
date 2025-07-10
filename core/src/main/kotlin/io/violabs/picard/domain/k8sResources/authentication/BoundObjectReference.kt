package io.violabs.picard.domain.k8sResources.authentication

import io.violabs.picard.common.DslBuilder

data class BoundObjectReference(
    val apiVersion: String? = null,
    val kind: String? = null,
    val name: String? = null,
    val uid: String? = null
) {
    class Builder : DslBuilder<BoundObjectReference> {
        var apiVersion: String? = null
        var kind: String? = null
        var name: String? = null
        var uid: String? = null

        override fun build(): BoundObjectReference {
            return BoundObjectReference(
                apiVersion = apiVersion,
                kind = kind,
                name = name,
                uid = uid
            )
        }
    }
}
