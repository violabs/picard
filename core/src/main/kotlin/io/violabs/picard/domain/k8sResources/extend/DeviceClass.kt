package io.violabs.picard.domain.k8sResources.extend

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

class DeviceClass(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    override val metadata: ObjectMetadata? = null
) : K8sResource<DeviceClass.Version> {

    interface Version : APIVersion
}