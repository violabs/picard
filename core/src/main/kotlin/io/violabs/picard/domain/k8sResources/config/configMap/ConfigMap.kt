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
}