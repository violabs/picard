package io.violabs.picard.domain.k8sResources.service.ingressClass

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder

data class IngressClassParametersReference(
    val kind: String,
    val name: String,
    val apiGroup: String? = null,
    val namespace: String? = null,
    val scope: String? = null
) {
    class Builder : DSLBuilder<IngressClassParametersReference> {
        var kind: String? = null
        var name: String? = null
        var apiGroup: String? = null
        var namespace: String? = null
        var scope: String? = null

        override fun build(): IngressClassParametersReference {
            return IngressClassParametersReference(
                kind = vRequireNotNull(this::kind),
                name = vRequireNotNull(this::name),
                apiGroup = apiGroup,
                namespace = namespace,
                scope = scope
            )
        }
    }
}