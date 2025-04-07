package io.violabs.picard.domain.k8sResources.workload.statefulSet

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class StatefulSetList(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val items: List<StatefulSet>,
    override val metadata: ListMeta? = null
) : K8sListResource<StatefulSetList.Version, StatefulSet> {
    interface Version : APIVersion
}