package io.violabs.picard.domain.k8sResources.cluster

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

class RuntimeClass(
    override val apiVersion: Version = KAPIVersion.NodeV1,
    override val metadata: ObjectMetadata? = null
) : K8sResource<RuntimeClass.Version> {

    interface Version : APIVersion
}