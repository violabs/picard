package io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Quantity

data class CSIStorageCapacity(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    val storageClassName: String,
    override val metadata: ObjectMetadata? = null,
    val capacity: Quantity? = null,
    val maximumVolumeSize: Quantity? = null,
    val nodeTopology: LabelSelector? = null
) : K8sResource<CSIStorageCapacity.Version> {
    interface Version : APIVersion
}