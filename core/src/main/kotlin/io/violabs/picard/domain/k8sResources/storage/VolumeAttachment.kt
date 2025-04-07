package io.violabs.picard.domain.k8sResources.storage

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

class VolumeAttachment(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val metadata: ObjectMetadata? = null
) : K8sResource<VolumeAttachment.Version> {

    interface Version : APIVersion
}