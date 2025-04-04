package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.APIVersion
import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.ListMeta

data class StorageClassList(
    override val apiVersion: Version = Version.STORAGE_V1,
    val metadata: ListMeta,
    val items: List<StorageClass>
) : K8sResource<StorageClassList.Version> {
    override val kind: Kind = Kind.STORAGE_CLASS_LIST

    enum class Version(override val ref: String? = null) : APIVersion {
        STORAGE_V1("storage.k8s.io/v1");

        override fun toString(): String = refString()
    }
}