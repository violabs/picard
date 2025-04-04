package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.*


data class StorageClass(
    override val apiVersion: Version = Version.STORAGE_V1,
    val metadata: ObjectMetadata,
    val provisioner: String,
    val allowVolumeExpansion: Boolean? = null,
    val allowedTopologies: List<TopologySelector.Term>? = null,
    val mountOptions: List<String>? = null,
    val parameters: Map<String, String>? = null,
    val reclaimPolicy: String? = null,
    val volumeBindingMode: String? = null,
) : K8sResource<StorageClass.Version> {
    override val kind: Kind = Kind.STORAGE_CLASS

    enum class Version(override val ref: String? = null) : APIVersion {
        STORAGE_V1("storage.k8s.io/v1");
        override fun toString(): String = refString()
    }
}