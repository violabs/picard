package io.violabs.picard.domain.k8sResources.storage.storageClass

import io.violabs.picard.common.ResourceDSLBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion


data class StorageClass(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    val provisioner: String,
    override val metadata: ObjectMetadata? = null,
    val allowVolumeExpansion: Boolean? = null,
    val allowedTopologies: List<TopologySelectorTerm>? = null,
    val mountOptions: List<String>? = null,
    val parameters: Map<String, String>? = null,
    val reclaimPolicy: String? = null,
    val volumeBindingMode: String? = null,
) : K8sResource<StorageClass.Version> {
    interface Version : APIVersion

    class Builder : ResourceDSLBuilder<StorageClass>() {
        var provisioner: String? = null
        private var allowVolumeExpansion: Boolean? = null
        private var allowedTopologies: List<TopologySelectorTerm>? = null
        private var mountOptions: List<String>? = null
        private var parameters: Map<String, String>? = null
        var reclaimPolicy: String? = null
        var volumeBindingMode: String? = null

        fun allowVolumeExpansion(value: Boolean = true) {
            allowVolumeExpansion = value
        }

        fun allowedTopologies(scope: TopologySelectorTerm.Group.() -> Unit) {
            allowedTopologies = TopologySelectorTerm.Group().apply(scope).terms()
        }

        fun mountOptions(vararg options: String) {
            mountOptions = options.toList()
        }

        fun parameters(vararg params: Pair<String, String>) {
            parameters = params.toMap()
        }

        override fun build(): StorageClass {
            return StorageClass(
                provisioner = vRequireNotNull(this::provisioner),
                metadata = metadata,
                allowVolumeExpansion = allowVolumeExpansion,
                allowedTopologies = allowedTopologies,
                mountOptions = mountOptions,
                parameters = parameters,
                reclaimPolicy = reclaimPolicy,
                volumeBindingMode = volumeBindingMode
            )
        }
    }

    class Group : K8sListResource.ItemGroup<StorageClass, Builder>(Builder()) {
        fun storageClass(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}