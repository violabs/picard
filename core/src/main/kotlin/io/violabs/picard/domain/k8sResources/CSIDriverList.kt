package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.APIVersion
import io.violabs.picard.domain.Kind

data class CSIDriverList(
    override val apiVersion: Version = Version.STORAGE_V1,
) : K8sResource<CSIDriverList.Version> {
    override val kind: Kind = Kind.CSI_DRIVER_LIST

    enum class Version(override val ref: String? = null) : APIVersion {
        STORAGE_V1("storage.k8s.io/v1");

        override fun toString(): String = refString()
    }
}