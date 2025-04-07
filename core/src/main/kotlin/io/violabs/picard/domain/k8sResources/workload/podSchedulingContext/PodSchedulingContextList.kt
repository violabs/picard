package io.violabs.picard.domain.k8sResources.workload.podSchedulingContext

import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class PodSchedulingContextList(
    override val apiVersion: Version = KAPIVersion.ResourceV1Alpha3,
    override val items: List<PodSchedulingContext>,
    override val metadata: ListMeta? = null
) : K8sListResource<PodSchedulingContextList.Version, PodSchedulingContext> {
    interface Version : APIVersion
}