package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

class PriorityLevelConfiguration(
    override val apiVersion: Version = KAPIVersion.FlowControlApiServerV1,
    override val metadata: ObjectMetadata? = null
) : K8sResource<PriorityLevelConfiguration.Version> {

    interface Version : APIVersion
}