package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DslBuilder

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
}