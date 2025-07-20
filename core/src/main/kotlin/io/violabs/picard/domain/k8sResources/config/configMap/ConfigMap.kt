package io.violabs.picard.domain.k8sResources.config.configMap

import io.violabs.picard.common.ResourceDslBuilder
import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.TextData
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ConfigResource

//todo: binary and text data should not share the same key - refactor
/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/config-map-v1/
 */
@Deprecated("Use v2 version", ReplaceWith("io.violabs.picard.v2.resources.config.map.ConfigMapV2"))
data class ConfigMap(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val binaryData: BinaryData? = null,
    val data: TextData? = null,
    val immutable: Boolean? = null
) : ConfigResource<ConfigMap.Version, ObjectMetadata> {
    interface Version : APIVersion

    class Builder : ResourceDslBuilder<ConfigMap>() {
        private var binaryData: BinaryData? = null
        private var data: TextData? = null
        private var immutable: Boolean? = null

        fun binaryData(vararg bytes: Pair<String, String>) {
            binaryData = BinaryData(bytes.toMap().toMutableMap())
        }

        fun binaryData(type: BinaryData.Type, vararg bytes: Pair<String, String>) {
            binaryData = BinaryData(bytes.toMap().toMutableMap(), type)
        }

        fun data(vararg strings: Pair<String, String>) {
            data = TextData(strings.toMap().toMutableMap())
        }

        fun immutable(value: Boolean = true) {
            immutable = value
        }

        override fun build(): ConfigMap {
            return ConfigMap(
                metadata = metadata,
                binaryData = binaryData,
                data = data,
                immutable = immutable
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ConfigMap, Builder>(Builder()) {
        fun map(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}