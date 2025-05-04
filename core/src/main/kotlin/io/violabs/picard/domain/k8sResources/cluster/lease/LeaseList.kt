package io.violabs.picard.domain.k8sResources.cluster.lease

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterListResource

data class LeaseList(
    override val apiVersion: Version = KAPIVersion.CoordinationV1,
    override val items: List<Lease>,
    override val metadata: ListMeta? = null
) : ClusterListResource<LeaseList.Version, Lease> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        Lease,
        Lease.Builder,
        Lease.Group,
        LeaseList
        >(Lease.Group()) {

        override fun build(): LeaseList {
            return LeaseList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
