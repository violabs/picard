package io.violabs.picard.domain.k8sResources.workload.replicaSet

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadListResource

data class ReplicaSetList(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val items: List<ReplicaSet>,
    override val metadata: ListMeta? = null
) : WorkloadListResource<ReplicaSetList.Version, ReplicaSet> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        ReplicaSet,
        ReplicaSet.Builder,
        ReplicaSet.Group,
        ReplicaSetList>(ReplicaSet.Group()) {

        override fun build(): ReplicaSetList {
            return ReplicaSetList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}