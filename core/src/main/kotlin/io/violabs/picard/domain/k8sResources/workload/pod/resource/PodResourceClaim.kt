package io.violabs.picard.domain.k8sResources.workload.pod.resource

import io.violabs.picard.domain.BaseResourceClaim
import io.violabs.picard.domain.DslBuilder

data class PodResourceClaim(
    val name: String,
    val resourceClaimName: String? = null,
    val resourceClaimTemplateName: String? = null
) : BaseResourceClaim {
    data class Status(
        val name: String,
        val resourceClaimName: String? = null
    )

    class Builder : DslBuilder<PodResourceClaim> {
        var name: String? = null
        var resourceClaimName: String? = null
        var resourceClaimTemplateName: String? = null

        override fun build(): PodResourceClaim {
            return PodResourceClaim(
                name = requireNotNull(name),
                resourceClaimName = resourceClaimName,
                resourceClaimTemplateName = resourceClaimTemplateName
            )
        }
    }
}