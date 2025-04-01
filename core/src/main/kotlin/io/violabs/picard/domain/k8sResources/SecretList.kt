package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.APIVersion
import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.ListMeta

data class SecretList(
    override val apiVersion: Version = Version.V1,
    val metadata: ListMeta,
    val items: List<Secret>
) : K8sResource<SecretList.Version> {
    override val kind: Kind = Kind.SECRET_LIST

    enum class Version(override val ref: String? = null) : APIVersion {
        V1;

        override fun toString(): String = refString()
    }
}