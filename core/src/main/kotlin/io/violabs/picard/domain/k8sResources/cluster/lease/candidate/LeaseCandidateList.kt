package io.violabs.picard.domain.k8sResources.cluster.lease.candidate

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterListResource

data class LeaseCandidateList(
    override val apiVersion: Version = KAPIVersion.CoordinationV1Alpha1,
    override val items: List<LeaseCandidate>,
    override val metadata: ListMeta? = null
) : ClusterListResource<LeaseCandidateList.Version, LeaseCandidate> {
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
