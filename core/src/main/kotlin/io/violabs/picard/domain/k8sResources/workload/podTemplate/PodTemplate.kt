package io.violabs.picard.domain.k8sResources.workload.podTemplate

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.k8sResources.workload.pod.Pod

data class PodTemplate(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : K8sResource<PodTemplate.Version> {
    data class Spec(
        val metadata: ObjectMetadata? = null,
        val spec: Pod.Spec? = null
    ) : BaseSpec

    interface Version : APIVersion
}