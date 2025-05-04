package io.violabs.picard.domain.k8sResources.workload.priorityClass

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class PriorityClassList(
    override val apiVersion: Version = KAPIVersion.SchedulingV1,
    override val items: List<PriorityClass>,
    override val metadata: ListMeta? = null
) : K8sListResource<PriorityClassList.Version, PriorityClass> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
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