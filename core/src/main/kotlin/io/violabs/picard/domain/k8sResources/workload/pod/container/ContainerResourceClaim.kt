package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder

data class ContainerResourceClaim(
    val name: String,
    val request: String? = null
) {
    class Builder : DslBuilder<ContainerResourceClaim> {
        var name: String? = null
        var request: String? = null
        override fun build(): ContainerResourceClaim {
            return ContainerResourceClaim(
                vRequireNotNull(this::name),
                request
            )
        }
    }

    class Group : BuilderGroup<ContainerResourceClaim, Builder>(Builder()) {
        fun claims(): List<ContainerResourceClaim>? = items()

        fun addContainerResourceClaim(block: Builder.() -> Unit) {
            add(block)
        }
    }
}