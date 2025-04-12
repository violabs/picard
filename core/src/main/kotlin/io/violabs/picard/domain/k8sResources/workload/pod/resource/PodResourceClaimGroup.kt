package io.violabs.picard.domain.k8sResources.workload.pod.resource


import io.violabs.picard.domain.BuilderGroup

class PodResourceClaimGroup : BuilderGroup<PodResourceClaim, PodResourceClaim.Builder>(PodResourceClaim.Builder()) {
    fun resourceClaims(): MutableList<PodResourceClaim>? = items()

    fun resourceClaim(scope: PodResourceClaim.Builder.() -> Unit) {
        add(scope)
    }
}