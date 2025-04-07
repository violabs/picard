package io.violabs.picard.domain.k8sResources.workload.priorityClass

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

class PriorityClass(
    override val apiVersion: Version = KAPIVersion.SchedulingV1,
    override val metadata: ObjectMetadata? = null,
    val value: Int,
    val description: String? = null,
    val globalDefault: Boolean? = null,
    val preemptionPolicy: String? = null
) : K8sResource<PriorityClass.Version> {
    interface Version : APIVersion
}