package io.violabs.picard.domain.k8sResources.cluster.namespace

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.Condition

class Namespace(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<Namespace.Version> {
    interface Version : APIVersion

    data class Spec(
        val finalizers: List<String>? = null
    ) : BaseSpec

    data class Status(
        val conditions: List<Condition>? = null,
        val phase: String? = null
    ) : BaseStatus
}