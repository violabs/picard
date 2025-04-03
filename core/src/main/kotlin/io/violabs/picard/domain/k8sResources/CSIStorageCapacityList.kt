package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.*

data class CSIStorageCapacityList(
    override val apiVersion: Version = Version.STORAGE_V1,
    val items: List<CSIStorageCapacity>,
    val metadata: ListMeta? = null
) : K8sResource<CSIStorageCapacityList.Version> {
    override val kind: Kind = Kind.CSI_STORAGE_CAPACITY_LIST

    enum class Version(override val ref: String? = null) : APIVersion {
        STORAGE_V1("storage.k8s.io/v1");

        override fun toString(): String = refString()
    }
}