package io.violabs.picard.domain.k8sResources.storage.volumeAttributesClass

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.ResourceDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class VolumeAttributesClass(
    override val apiVersion: Version = KAPIVersion.StorageV1Beta1,
    override val metadata: ObjectMetadata? = null,
    val driverName: String? = null,
    val parameters: Map<String, String>? = null
) : K8sResource<VolumeAttributesClass.Version> {
    interface Version : APIVersion

    class Builder : ResourceDSLBuilder<VolumeAttributesClass>() {
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
        fun attributesClass(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}