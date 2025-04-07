package io.violabs.picard.domain.k8sResources.cluster.ipAddress

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.BaseSpec

class IPAddress(
    override val apiVersion: Version = KAPIVersion.NetworkingV1Beta1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : K8sResource<IPAddress.Version> {
    interface Version : APIVersion

    data class Spec(val parentRef: ParentReference) : BaseSpec
}