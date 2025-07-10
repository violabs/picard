package io.violabs.picard.domain.k8sResources.workload.statefulSet

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadListResource

data class StatefulSetList(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val items: List<StatefulSet>,
    override val metadata: ListMeta? = null
) : WorkloadListResource<StatefulSetList.Version, StatefulSet> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
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