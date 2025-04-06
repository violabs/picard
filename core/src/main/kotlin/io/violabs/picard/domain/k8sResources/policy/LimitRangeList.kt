package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class LimitRangeList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<LimitRange>,
    override val metadata: ListMeta? = null
) : K8sListResource<LimitRangeList.Version, LimitRange> {
    interface Version : APIVersion
}
