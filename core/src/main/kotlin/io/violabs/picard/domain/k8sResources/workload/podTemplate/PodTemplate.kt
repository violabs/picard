package io.violabs.picard.domain.k8sResources.workload.podTemplate

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.workload.pod.Pod

data class PodTemplate(
    override val apiVersion: Version = Version.V1,
    val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : K8sResource<PodTemplate.Version> {
    override val kind: Kind = Kind.POD_TEMPLATE

    data class Spec(
        val metadata: ObjectMetadata? = null,
        val spec: _root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.Spec? = null
    )
    enum class Version(override val ref: String? = null) : APIVersion {
        V1;
        override fun toString(): String = refString()
    }
}