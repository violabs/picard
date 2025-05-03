package io.violabs.picard.domain.k8sResources.workload.resourceClaim

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder

data class ConsumerReference(
    val name: String,
    val resource: String,
    val uid: String,
    val apiGroup: String? = null
) {
    class Builder : DSLBuilder<ConsumerReference> {
        var name: String? = null
        var resource: String? = null
        var uid: String? = null
        var apiGroup: String? = null

        override fun build(): ConsumerReference {
            return ConsumerReference(
                name = vRequireNotNull(this::name),
                resource = vRequireNotNull(this::resource),
                uid = vRequireNotNull(this::uid),
                apiGroup = apiGroup
            )
        }
    }

    class Group : BuilderGroup<ConsumerReference, Builder>(Builder()) {
        fun references(): List<ConsumerReference>? = items()

        fun reference(block: Builder.() -> Unit) {
            add(block)
        }
    }
}