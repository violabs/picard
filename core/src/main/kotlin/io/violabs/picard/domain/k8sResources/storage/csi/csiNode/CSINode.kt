package io.violabs.picard.domain.k8sResources.storage.csi.csiNode

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.BaseSpec

data class CSINode(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : K8sResource<CSINode.Version> {
    interface Version : APIVersion

    class Spec(
        val drivers: List<CSINodeDriver>
    ) : BaseSpec
}