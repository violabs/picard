package io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity

import io.violabs.picard.common.ResourceDSLBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.label.LabelSelector
import io.violabs.picard.domain.manifest.StorageResource

data class CSIStorageCapacity(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    val storageClassName: String,
    override val metadata: ObjectMetadata? = null,
    val capacity: Quantity? = null,
    val maximumVolumeSize: Quantity? = null,
    val nodeTopology: LabelSelector? = null
) : StorageResource<CSIStorageCapacity.Version> {
    interface Version : APIVersion

    class Builder : ResourceDSLBuilder<CSIStorageCapacity>() {
        var storageClassName: String? = null
        private var capacity: Quantity? = null
        private var maximumVolumeSize: Quantity? = null
        private var nodeTopology: LabelSelector? = null

        fun capacity(value: String) {
            this.capacity = Quantity(value)
        }

        fun maximumVolumeSize(value: String) {
            this.maximumVolumeSize = Quantity(value)
        }

        fun nodeTopology(block: LabelSelector.Builder.() -> Unit) {
            this.nodeTopology = LabelSelector.Builder().apply(block).build()
        }

        override fun build(): CSIStorageCapacity {
            return CSIStorageCapacity(
                storageClassName = vRequireNotNull(this::storageClassName),
                metadata = metadata,
                capacity = capacity,
                maximumVolumeSize = maximumVolumeSize,
                nodeTopology = nodeTopology
            )
        }
    }

    class Group : K8sListResource.ItemGroup<CSIStorageCapacity, Builder>(Builder()) {
        fun csiStorageCapacityItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}