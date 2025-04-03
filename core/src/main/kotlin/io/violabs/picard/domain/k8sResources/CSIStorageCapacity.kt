package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.*

data class CSIStorageCapacity(
    override val apiVersion: Version = Version.STORAGE_V1,
    val storageClassName: String,
    val metadata: ObjectMetadata? = null,
    val capacity: Quantity? = null,
    val maximumVolumeSize: Quantity? = null,
    val nodeTopology: LabelSelector? = null
) : K8sResource<CSIStorageCapacity.Version> {
    override val kind: Kind = Kind.CSI_STORAGE_CAPACITY

    enum class Version(override val ref: String? = null) : APIVersion {
        STORAGE_V1("storage.k8s.io/v1");

        override fun toString(): String = refString()
    }
}