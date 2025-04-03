package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.*

//todo: binary and text data should not share the same key - refactor
/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/config-map-v1/
 */
data class ConfigMap(
    override val apiVersion: Version = Version.V1,
    val metadata: ObjectMetadata,
    val binaryData: BinaryData? = null,
    val data: TextData? = null,
    val immutable: Boolean? = null
) : K8sResource<ConfigMap.Version> {
    override val kind: Kind = Kind.CONFIG_MAP

    enum class Version(override val ref: String? = null) : APIVersion {
        V1;
        override fun toString(): String = refString()
    }
}