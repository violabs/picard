package io.violabs.picard.domain.k8sResources.workload.controllerRevision

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadListResource

data class ControllerRevisionList(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val items: List<ControllerRevision>,
    override val metadata: ListMeta? = null
) : WorkloadListResource<ControllerRevisionList.Version, ControllerRevision> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        ControllerRevision,
        ControllerRevision.Builder,
        ControllerRevision.Group,
        ControllerRevisionList>(ControllerRevision.Group()) {

        override fun build(): ControllerRevisionList {
            return ControllerRevisionList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}