package io.violabs.picard.domain.k8sResources.workload.controllerRevision

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class ControllerRevision(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    val revision: Long,
    override val metadata: ObjectMetadata? = null,
    val data: Any? = null
) : K8sResource<ControllerRevision.Version> {
    interface Version : APIVersion
}