package io.violabs.picard.domain.k8sResources.config.configMap

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseEnvSource

//todo: binary and text data should not share the same key - refactor
/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/config-map-v1/
 */
data class ConfigMap(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val binaryData: BinaryData? = null,
    val data: TextData? = null,
    val immutable: Boolean? = null
) : K8sResource<ConfigMap.Version> {
    interface Version : APIVersion

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

    data class KeySelector(
        val key: String,
        val name: String? = null,
        val optional: Boolean? = null
    )

    data class EnvSource(
        val name: String? = null,
        val optional: Boolean? = null
    ) : BaseEnvSource
}