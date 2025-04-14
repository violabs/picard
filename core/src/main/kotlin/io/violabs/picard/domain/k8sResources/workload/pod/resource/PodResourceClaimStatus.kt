package io.violabs.picard.domain.k8sResources.workload.pod.resource

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DslBuilder

data class PodResourceClaimStatus(
    val name: String,
    val resourceClaimName: String? = null
) {
    class Builder : DslBuilder<PodResourceClaimStatus> {
        var name: String? = null
        var resourceClaimName: String? = null
        override fun build(): PodResourceClaimStatus  {
            return PodResourceClaimStatus(
                name = vRequireNotNull(this::name),
                resourceClaimName = resourceClaimName
            )
        }
    }
}