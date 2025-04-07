package io.violabs.picard.domain.k8sResources.cluster.runtimeClass

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.*

class RuntimeClass(
    override val apiVersion: Version = KAPIVersion.NodeV1,
    override val metadata: ObjectMetadata? = null,
    val handler: String,
    val overhead: Overhead? = null,

    ) : K8sResource<RuntimeClass.Version> {
    interface Version : APIVersion

    data class Overhead(val podFixed: Map<String, Quantity>? = null)

    data class Scheduling(
        val nodeSelector: Map<String, String>? = null,
        val tolerations: List<Toleration>? = null
    )
}