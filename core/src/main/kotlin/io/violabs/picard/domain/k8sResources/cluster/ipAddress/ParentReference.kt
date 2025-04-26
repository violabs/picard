package io.violabs.picard.domain.k8sResources.cluster.ipAddress

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder

data class ParentReference(
    val name: String,
    val resource: String,
    val group: String? = null,
    val namespace: String? = null
) {
    class Builder : DSLBuilder<ParentReference> {
        var name: String? = null
        var resource: String? = null
        var group: String? = null
        var namespace: String? = null

        override fun build(): ParentReference {
            return ParentReference(
                name = vRequireNotNull(this::name),
                resource = vRequireNotNull(this::resource),
                group = group,
                namespace = namespace
            )
        }
    }
}