package io.violabs.picard.domain.k8sResources.storage.volumeAttributesClass

import io.violabs.picard.common.ResourceDslBuilder
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.StorageResource

data class VolumeAttributesClass(
    override val apiVersion: Version = KAPIVersion.StorageV1Beta1,
    override val metadata: ObjectMetadata? = null,
    val driverName: String? = null,
    val parameters: Map<String, String>? = null
) : StorageResource<VolumeAttributesClass.Version> {
    interface Version : APIVersion

    class Builder : ResourceDslBuilder<VolumeAttributesClass>() {
        var driverName: String? = null
        private var parameters: Map<String, String>? = null

        fun parameters(vararg values: Pair<String, String>) {
            parameters = values.toMap()
        }

        override fun build(): VolumeAttributesClass {
            return VolumeAttributesClass(
                metadata = metadata,
                driverName = driverName,
                parameters = parameters
            )
        }
    }

    class Group : K8sListResource.ItemGroup<VolumeAttributesClass, Builder>(Builder()) {
        fun volumeAttributesClassItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}