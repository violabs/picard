package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.APIVersion
import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.ListMeta

class CSINodeList(
    override val apiVersion: Version = Version.STORAGE_V1,
    val metadata: ListMeta,
    val items: List<CSINode>
) : K8sResource<CSINodeList.Version> {
    override val kind: Kind = Kind.CSI_NODE_LIST

    enum class Version(override val ref: String? = null) : APIVersion {
        STORAGE_V1("storage.k8s.io/v1");

        override fun toString(): String = refString()
    }
}