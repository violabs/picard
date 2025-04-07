package io.violabs.picard.domain.k8sResources.cluster.componentStatus

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.ComponentCondition

class ComponentStatus(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val conditions: List<ComponentCondition>? = null
) : K8sResource<ComponentStatus.Version> {

    interface Version : APIVersion
}