package io.violabs.picard.domain.k8sResources.workload.priorityClass

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadListResource

data class PriorityClassList(
    override val apiVersion: Version = KAPIVersion.SchedulingV1,
    override val items: List<PriorityClass>,
    override val metadata: ListMeta? = null
) : WorkloadListResource<PriorityClassList.Version, PriorityClass> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        PriorityClass,
        PriorityClass.Builder,
        PriorityClass.Group,
        PriorityClassList>(PriorityClass.Group()) {

        override fun build(): PriorityClassList {
            return PriorityClassList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}