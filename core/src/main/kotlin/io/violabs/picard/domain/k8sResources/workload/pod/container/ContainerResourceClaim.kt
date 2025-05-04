package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder

data class ContainerResourceClaim(
    val name: String,
    val request: String? = null
) {
    class Builder : DSLBuilder<ContainerResourceClaim> {
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