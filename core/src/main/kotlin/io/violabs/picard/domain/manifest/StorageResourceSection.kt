package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.storage.csi.csiDriver.CSIDriver
import io.violabs.picard.domain.k8sResources.storage.csi.csiDriver.CSIDriverList
import io.violabs.picard.domain.k8sResources.storage.csi.csiNode.CSINode
import io.violabs.picard.domain.k8sResources.storage.csi.csiNode.CSINodeList
import io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity.CSIStorageCapacity
import io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity.CSIStorageCapacityList
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.PersistentVolume
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.PersistentVolumeList
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim.PersistentVolumeClaim
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim.PersistentVolumeClaimList
import io.violabs.picard.domain.k8sResources.storage.storageClass.StorageClass
import io.violabs.picard.domain.k8sResources.storage.storageClass.StorageClassList
import io.violabs.picard.domain.k8sResources.storage.storageVersionMigration.StorageVersionMigration
import io.violabs.picard.domain.k8sResources.storage.storageVersionMigration.StorageVersionMigrationList
import io.violabs.picard.domain.k8sResources.storage.volumeAttachment.VolumeAttachment
import io.violabs.picard.domain.k8sResources.storage.volumeAttachment.VolumeAttachmentList
import io.violabs.picard.domain.k8sResources.storage.volumeAttributesClass.VolumeAttributesClass
import io.violabs.picard.domain.k8sResources.storage.volumeAttributesClass.VolumeAttributesClassList

interface StorageResource<T : APIVersion> : K8sResource<T>
interface StorageListResource<T : APIVersion, E> : K8sListResource<T, E>

data class StorageResourceSection(
    override val resources: List<K8sAPIResource<*>>
) : ManifestResource {

    class Builder(
        private val resources: MutableList<StorageResource<*>> = mutableListOf(),
        private val lists: MutableList<StorageListResource<*, *>> = mutableListOf()
    ) : DSLBuilder<StorageResourceSection> {
        fun csiDriver(block: CSIDriver.Builder.() -> Unit) {
            resources += CSIDriver.Builder().apply(block).build()
        }

        fun csiDriverList(block: CSIDriverList.Builder.() -> Unit) {
            lists += CSIDriverList.Builder().apply(block).build()
        }

        fun csiNode(block: CSINode.Builder.() -> Unit) {
            resources += CSINode.Builder().apply(block).build()
        }

        fun csiNodeList(block: CSINodeList.Builder.() -> Unit) {
            lists += CSINodeList.Builder().apply(block).build()
        }

        fun csiStorageCapacity(block: CSIStorageCapacity.Builder.() -> Unit) {
            resources += CSIStorageCapacity.Builder().apply(block).build()
        }

        fun csiStorageCapacityList(block: CSIStorageCapacityList.Builder.() -> Unit) {
            lists += CSIStorageCapacityList.Builder().apply(block).build()
        }

        fun persistentVolumeClaim(block: PersistentVolumeClaim.Builder.() -> Unit) {
            resources += PersistentVolumeClaim.Builder().apply(block).build()
        }

        fun persistentVolumeClaimList(block: PersistentVolumeClaimList.Builder.() -> Unit) {
            lists += PersistentVolumeClaimList.Builder().apply(block).build()
        }

        fun persistentVolume(block: PersistentVolume.Builder.() -> Unit) {
            resources += PersistentVolume.Builder().apply(block).build()
        }

        fun persistentVolumeList(block: PersistentVolumeList.Builder.() -> Unit) {
            lists += PersistentVolumeList.Builder().apply(block).build()
        }

        fun storageClass(block: StorageClass.Builder.() -> Unit) {
            resources += StorageClass.Builder().apply(block).build()
        }

        fun storageClassList(block: StorageClassList.Builder.() -> Unit) {
            lists += StorageClassList.Builder().apply(block).build()
        }

        fun storageVersionMigration(block: StorageVersionMigration.Builder.() -> Unit) {
            resources += StorageVersionMigration.Builder().apply(block).build()
        }

        fun storageVersionMigrationList(block: StorageVersionMigrationList.Builder.() -> Unit) {
            lists += StorageVersionMigrationList.Builder().apply(block).build()
        }

        fun volumeAttachment(block: VolumeAttachment.Builder.() -> Unit) {
            resources += VolumeAttachment.Builder().apply(block).build()
        }

        fun volumeAttachmentList(block: VolumeAttachmentList.Builder.() -> Unit) {
            lists += VolumeAttachmentList.Builder().apply(block).build()
        }

        fun volumeAttributesClass(block: VolumeAttributesClass.Builder.() -> Unit) {
            resources += VolumeAttributesClass.Builder().apply(block).build()
        }

        fun volumeAttributesClassList(block: VolumeAttributesClassList.Builder.() -> Unit) {
            lists += VolumeAttributesClassList.Builder().apply(block).build()
        }


        override fun build(): StorageResourceSection {
            return StorageResourceSection(
                vRequireNotEmpty(
                    (resources + lists).sortedBy { it::class.simpleName },
                    "resources"
                )
            )
        }
    }
}