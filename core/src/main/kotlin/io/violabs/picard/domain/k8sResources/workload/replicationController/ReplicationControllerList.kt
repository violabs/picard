package io.violabs.picard.domain.k8sResources.workload.replicationController

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadListResource

data class ReplicationControllerList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<ReplicationController>,
    override val metadata: ListMeta? = null
) : WorkloadListResource<ReplicationControllerList.Version, ReplicationController> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        ReplicationController,
        ReplicationController.Builder,
        ReplicationController.Group,
        ReplicationControllerList>(ReplicationController.Group()) {

        override fun build(): ReplicationControllerList {
            return ReplicationControllerList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}