package io.violabs.picard.domain.k8sResources.workload.priorityClass

import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class PriorityClassList(
    override val apiVersion: Version = KAPIVersion.SchedulingV1,
    override val items: List<PriorityClass>,
    override val metadata: ListMeta? = null
) : K8sListResource<PriorityClassList.Version, PriorityClass> {
    interface Version : APIVersion
}