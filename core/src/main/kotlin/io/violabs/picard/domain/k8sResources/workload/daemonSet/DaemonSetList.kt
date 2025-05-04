package io.violabs.picard.domain.k8sResources.workload.daemonSet

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource


data class DaemonSetList(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val items: List<DaemonSet>,
    override val metadata: ListMeta? = null
) : K8sListResource<DaemonSetList.Version, DaemonSet> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        DaemonSet,
        DaemonSet.Builder,
        DaemonSet.Group,
        DaemonSetList
        >(DaemonSet.Group()) {

        override fun build(): DaemonSetList {
            return DaemonSetList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}