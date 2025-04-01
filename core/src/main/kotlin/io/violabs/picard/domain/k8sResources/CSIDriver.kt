package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.*

data class CSIDriver(
    override val apiVersion: Version,
    val metadata: ObjectMetadata,

    ) : K8sResource<CSIDriver.Version> {
    override val kind: Kind = Kind.CSI_DRIVER

    enum class Version(override val ref: String? = null) : APIVersion {
        V1;

        override fun toString(): String = name.lowercase()
    }

    data class Spec(
        val attachRequired: Boolean,
        val podInfoOnMount: Boolean,
        val requiresRepublish: Boolean,
        val seLinuxMount: Boolean,
        val storageCapacity: Int,
        val fsGroupPolicy: String,
        val tokenRequests: List<TokenRequest>,
        val volumeLifecycleModes: List<String>
    )
}