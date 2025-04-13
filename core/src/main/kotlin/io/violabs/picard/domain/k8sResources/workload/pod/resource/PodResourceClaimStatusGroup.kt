package io.violabs.picard.domain.k8sResources.workload.pod.resource


import io.violabs.picard.domain.BuilderGroup

class PodResourceClaimStatusGroup :
    BuilderGroup<PodResourceClaimStatus, PodResourceClaimStatus.Builder>(PodResourceClaimStatus.Builder()) {
    fun statuses(): MutableList<PodResourceClaimStatus>? = items()

    fun claimStatus(scope: PodResourceClaimStatus.Builder.() -> Unit) {
        add(scope)
    }
}