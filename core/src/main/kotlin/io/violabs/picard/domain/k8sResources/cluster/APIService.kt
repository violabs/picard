package io.violabs.picard.domain.k8sResources.cluster

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

class APIService(
    override val apiVersion: Version = KAPIVersion.APIRegistrationV1,
    override val metadata: ObjectMetadata? = null
) : K8sResource<APIService.Version> {

    interface Version : APIVersion
}