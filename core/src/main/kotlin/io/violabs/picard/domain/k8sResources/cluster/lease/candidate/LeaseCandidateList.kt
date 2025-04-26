package io.violabs.picard.domain.k8sResources.cluster.lease.candidate

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class LeaseCandidateList(
    override val apiVersion: Version = KAPIVersion.CoordinationV1Alpha1,
    override val items: List<LeaseCandidate>,
    override val metadata: ListMeta? = null
) : K8sListResource<LeaseCandidateList.Version, LeaseCandidate> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        LeaseCandidate,
        LeaseCandidate.Builder,
        LeaseCandidate.Group,
        LeaseCandidateList
        >(LeaseCandidate.Group()) {

        override fun build(): LeaseCandidateList {
            return LeaseCandidateList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
