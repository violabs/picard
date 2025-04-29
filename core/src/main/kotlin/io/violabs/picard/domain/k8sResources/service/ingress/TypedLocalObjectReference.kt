package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder

data class TypedLocalObjectReference(
    val kind: String,
    val name: String,
    val apiGroup: String? = null
) {
    class Builder : DSLBuilder<TypedLocalObjectReference> {
        var kind: String? = null
        var name: String? = null
        var apiGroup: String? = null

        override fun build(): TypedLocalObjectReference {
            return TypedLocalObjectReference(
                kind = vRequireNotNull(this::kind),
                name = vRequireNotNull(this::name),
                apiGroup = apiGroup
            )
        }
    }
}