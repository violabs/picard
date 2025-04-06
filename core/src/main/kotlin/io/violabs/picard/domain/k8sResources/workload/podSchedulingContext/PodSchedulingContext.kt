package io.violabs.picard.domain.k8sResources.workload.podSchedulingContext

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.BaseSpec
import io.violabs.picard.domain.k8sResources.workload.BaseStatus

class PodSchedulingContext(
    override val apiVersion: Version = KAPIVersion.ResourceV1Alpha3,
    val spec: Spec,
    override val metadata: ObjectMetadata? = null,
    val status: Status? = null
) : K8sResource<PodSchedulingContext.Version> {
    interface Version : APIVersion

    data class Spec(
        val potentialNodes: List<String>? = null,
        val selectedNode: String? = null
    ) : BaseSpec

    data class Status(
        val resourceClaims: List<ResourceClaimSchedulingStatus>? = null
    ) : BaseStatus

    data class ResourceClaimSchedulingStatus(
        val name: String,
        val unsuitableNodes: List<String>? = null
    )
}