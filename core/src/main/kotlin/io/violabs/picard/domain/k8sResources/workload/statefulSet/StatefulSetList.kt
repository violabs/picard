package io.violabs.picard.domain.k8sResources.workload.statefulSet

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class StatefulSetList(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val items: List<StatefulSet>,
    override val metadata: ListMeta? = null
) : K8sListResource<StatefulSetList.Version, StatefulSet> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        StatefulSet,
        StatefulSet.Builder,
        StatefulSet.Group,
        StatefulSetList
        >(StatefulSet.Group()) {

        override fun build(): StatefulSetList {
            return StatefulSetList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}