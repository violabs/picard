package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.*

data class CSINode(
    override val apiVersion: Version = Version.STORAGE_V1,
    val metadata: ObjectMetadata,
    val spec: Spec
) : K8sResource<CSINode.Version> {
    override val kind: Kind = Kind.CSI_NODE

    enum class Version(override val ref: String? = null) : APIVersion {
        STORAGE_V1("storage.k8s.io/v1");

        override fun toString(): String = refString()
    }

    class Spec(
        val drivers: List<CSINodeDriver>
    )
}