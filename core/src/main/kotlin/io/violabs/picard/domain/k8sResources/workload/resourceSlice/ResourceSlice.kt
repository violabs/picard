package io.violabs.picard.domain.k8sResources.workload.resourceSlice

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.*

data class ResourceSlice(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    val spec: Spec,
    override val metadata: ObjectMetadata? = null,
) : K8sResource<ResourceSlice.Version> {
    interface Version : APIVersion

    data class Spec(
        val driver: String,
        val pool: ResourcePool,
        val allNodes: Boolean? = null,
        val devices: List<Device>? = null,
        val nodeName: String? = null,
        val nodeSelector: NodeSelector? = null
    ) : BaseSpec
}

data class ResourcePool(
    val generation: Long,
    val name: String,
    val resourceSliceCount: Long
)