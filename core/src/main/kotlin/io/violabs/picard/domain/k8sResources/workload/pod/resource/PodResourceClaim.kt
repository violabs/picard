package io.violabs.picard.domain.k8sResources.workload.pod.resource

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseResourceClaim
import io.violabs.picard.domain.DslBuilder

data class PodResourceClaim(
    val name: String,
    val resourceClaimName: String? = null,
    val resourceClaimTemplateName: String? = null
) : BaseResourceClaim {

    class Builder : DslBuilder<PodResourceClaim> {
        var name: String? = null
        var resourceClaimName: String? = null
        var resourceClaimTemplateName: String? = null

        override fun build(): PodResourceClaim {
            return PodResourceClaim(
                name = vRequireNotNull(this::name),
                resourceClaimName = resourceClaimName,
                resourceClaimTemplateName = resourceClaimTemplateName
            )
        }
    }
}