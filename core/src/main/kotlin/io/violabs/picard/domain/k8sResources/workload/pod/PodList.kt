package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadListResource

data class PodList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<Pod>,
    override val metadata: ListMeta? = null
) : WorkloadListResource<PodList.Version, Pod> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        Pod,
        Pod.Builder,
        Pod.Group,
        PodList
        >(Pod.Group()) {

        override fun build(): PodList {
            return PodList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}