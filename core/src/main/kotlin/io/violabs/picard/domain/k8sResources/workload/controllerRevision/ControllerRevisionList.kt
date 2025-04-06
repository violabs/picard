package io.violabs.picard.domain.k8sResources.workload.controllerRevision

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ControllerRevisionList(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val items: List<ControllerRevision>,
    override val metadata: ListMeta? = null
) : K8sListResource<ControllerRevisionList.Version, ControllerRevision> {
    interface Version : APIVersion
}