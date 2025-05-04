package io.violabs.picard.domain.k8sResources.policy.limitRange

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class LimitRangeList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<LimitRange>,
    override val metadata: ListMeta? = null
) : K8sListResource<LimitRangeList.Version, LimitRange> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        LimitRange,
        LimitRange.Builder,
        LimitRange.Group,
        LimitRangeList
        >(LimitRange.Group()) {

        override fun build(): LimitRangeList {
            return LimitRangeList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
