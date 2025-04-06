package io.violabs.picard.domain.k8sResources.cluster

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

class ComponentStatus(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null
) : K8sResource<ComponentStatus.Version> {

    interface Version : APIVersion
}