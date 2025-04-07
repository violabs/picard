package io.violabs.picard.domain.k8sResources.storage.storageClass

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion


data class StorageClass(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    val provisioner: String,
    override val metadata: ObjectMetadata? = null,
    val allowVolumeExpansion: Boolean? = null,
    val allowedTopologies: List<TopologySelector.Term>? = null,
    val mountOptions: List<String>? = null,
    val parameters: Map<String, String>? = null,
    val reclaimPolicy: String? = null,
    val volumeBindingMode: String? = null,
) : K8sResource<StorageClass.Version> {
    interface Version : APIVersion
}