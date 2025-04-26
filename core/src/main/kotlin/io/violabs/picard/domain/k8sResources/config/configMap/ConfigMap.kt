package io.violabs.picard.domain.k8sResources.config.configMap

import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.ResourceDSLBuilder
import io.violabs.picard.domain.TextData
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

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

    class Builder : ResourceDSLBuilder<ConfigMap>() {
        private var binaryData: BinaryData? = null
        private var data: TextData? = null
        private var immutable: Boolean? = null

        fun binaryData(vararg data: Pair<String, List<Byte>>) {
            binaryData = BinaryData(data.toMap().toMutableMap())
        }

        fun data(vararg data: Pair<String, String>) {
            this.data = TextData(data.toMap().toMutableMap())
        }

        fun immutable(value: Boolean = true) {
            this.immutable = value
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
        fun configMap(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}