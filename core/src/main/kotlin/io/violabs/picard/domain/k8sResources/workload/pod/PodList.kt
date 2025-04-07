package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class PodList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<Pod>,
    override val metadata: ListMeta? = null
) : K8sListResource<PodList.Version, Pod> {
    interface Version : APIVersion
}