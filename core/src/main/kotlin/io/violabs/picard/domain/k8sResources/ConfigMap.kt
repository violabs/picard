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

    data class VolumeSource(
        val name: String,
        val optional: Boolean? = null,
        val defaultMode: Int? = null,
        val items: List<KeyToPath>? = null
    )

    data class Projection(
        val name: String? = null,
        val optional: Boolean? = null,
        val items: List<KeyToPath>? = null
    )
}