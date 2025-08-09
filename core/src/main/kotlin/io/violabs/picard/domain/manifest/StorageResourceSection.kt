package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.v2.resources.storage.csi.driver.CsiDriverDslBuilder
import io.violabs.picard.v2.resources.storage.csi.driver.CsiDriverDslBuilderScope
import io.violabs.picard.v2.resources.storage.csi.driver.CsiDriverListDslBuilder
import io.violabs.picard.v2.resources.storage.csi.driver.CsiDriverListDslBuilderScope
import io.violabs.picard.v2.resources.storage.csi.node.CsiNodeDslBuilder
import io.violabs.picard.v2.resources.storage.csi.node.CsiNodeDslBuilderScope
import io.violabs.picard.v2.resources.storage.csi.node.CsiNodeListDslBuilder
import io.violabs.picard.v2.resources.storage.csi.node.CsiNodeListDslBuilderScope
import io.violabs.picard.v2.resources.storage.csi.storage.capacity.CsiStorageCapacityDslBuilder
import io.violabs.picard.v2.resources.storage.csi.storage.capacity.CsiStorageCapacityDslBuilderScope
import io.violabs.picard.v2.resources.storage.csi.storage.capacity.CsiStorageCapacityListDslBuilder
import io.violabs.picard.v2.resources.storage.csi.storage.capacity.CsiStorageCapacityListDslBuilderScope
import io.violabs.picard.v2.resources.storage.persistent.volume.PersistentVolumeDslBuilder
import io.violabs.picard.v2.resources.storage.persistent.volume.PersistentVolumeDslBuilderScope
import io.violabs.picard.v2.resources.storage.persistent.volume.PersistentVolumeListDslBuilder
import io.violabs.picard.v2.resources.storage.persistent.volume.PersistentVolumeListDslBuilderScope
import io.violabs.picard.v2.resources.storage.persistent.volume.claim.PersistentVolumeClaimDslBuilder
import io.violabs.picard.v2.resources.storage.persistent.volume.claim.PersistentVolumeClaimDslBuilderScope
import io.violabs.picard.v2.resources.storage.persistent.volume.claim.PersistentVolumeClaimListDslBuilder
import io.violabs.picard.v2.resources.storage.persistent.volume.claim.PersistentVolumeClaimListDslBuilderScope
import io.violabs.picard.v2.resources.storage.StorageClassDslBuilder
import io.violabs.picard.v2.resources.storage.StorageClassDslBuilderScope
import io.violabs.picard.v2.resources.storage.StorageClassListDslBuilder
import io.violabs.picard.v2.resources.storage.StorageClassListDslBuilderScope
import io.violabs.picard.v2.resources.storage.csi.driver.CsiDriver
import io.violabs.picard.v2.resources.storage.version.migration.StorageVersionMigrationDslBuilder
import io.violabs.picard.v2.resources.storage.version.migration.StorageVersionMigrationDslBuilderScope
import io.violabs.picard.v2.resources.storage.version.migration.StorageVersionMigrationListDslBuilder
import io.violabs.picard.v2.resources.storage.version.migration.StorageVersionMigrationListDslBuilderScope
import io.violabs.picard.v2.resources.storage.volume.attachment.VolumeAttachmentDslBuilder
import io.violabs.picard.v2.resources.storage.volume.attachment.VolumeAttachmentDslBuilderScope
import io.violabs.picard.v2.resources.storage.volume.attachment.VolumeAttachmentListDslBuilder
import io.violabs.picard.v2.resources.storage.volume.attachment.VolumeAttachmentListDslBuilderScope
import io.violabs.picard.v2.resources.storage.volume.VolumeAttributesClassDslBuilder
import io.violabs.picard.v2.resources.storage.volume.VolumeAttributesClassDslBuilderScope
import io.violabs.picard.v2.resources.storage.volume.VolumeAttributesClassListDslBuilder
import io.violabs.picard.v2.resources.storage.volume.VolumeAttributesClassListDslBuilderScope

interface StorageResource<T : APIVersion, META> : K8sResource<T, META>
interface StorageListResource<T : APIVersion, E> : K8sListResource<T, E>

data class StorageResourceSection(
    override val resources: List<K8sAPIResource<*>>
) : ManifestResource {

    class Builder(
        private val resources: MutableList<StorageResource<*, *>> = mutableListOf(),
        private val lists: MutableList<StorageListResource<*, *>> = mutableListOf()
    ) : DslBuilder<StorageResourceSection> {
        fun csiDriver(block: CsiDriverDslBuilderScope) {
            val csiDriver: CsiDriver = CsiDriverDslBuilder().apply(block).build()
            resources.add(csiDriver)
        }

        fun csiDriverList(block: CsiDriverListDslBuilderScope) {
            val list = CsiDriverListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun csiNode(block: CsiNodeDslBuilderScope) {
            val csiNode = CsiNodeDslBuilder().apply(block).build()
            resources.add(csiNode)
        }

        fun csiNodeList(block: CsiNodeListDslBuilderScope) {
            val list = CsiNodeListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun csiStorageCapacity(block: CsiStorageCapacityDslBuilderScope) {
            val csiStorageCapacity = CsiStorageCapacityDslBuilder().apply(block).build()
            resources.add(csiStorageCapacity)
        }

        fun csiStorageCapacityList(block: CsiStorageCapacityListDslBuilderScope) {
            val list = CsiStorageCapacityListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun persistentVolumeClaim(block: PersistentVolumeClaimDslBuilderScope) {
            val pvc = PersistentVolumeClaimDslBuilder().apply(block).build()
            resources.add(pvc)
        }

        fun persistentVolumeClaimList(block: PersistentVolumeClaimListDslBuilderScope) {
            val list = PersistentVolumeClaimListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun persistentVolume(block: PersistentVolumeDslBuilderScope) {
            val pv = PersistentVolumeDslBuilder().apply(block).build()
            resources.add(pv)
        }

        fun persistentVolumeList(block: PersistentVolumeListDslBuilderScope) {
            val list = PersistentVolumeListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun storageClass(block: StorageClassDslBuilderScope) {
            val storageClass = StorageClassDslBuilder().apply(block).build()
            resources.add(storageClass)
        }

        fun storageClassList(block: StorageClassListDslBuilderScope) {
            val list = StorageClassListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun storageVersionMigration(block: StorageVersionMigrationDslBuilderScope) {
            val migration = StorageVersionMigrationDslBuilder().apply(block).build()
            resources.add(migration)
        }

        fun storageVersionMigrationList(block: StorageVersionMigrationListDslBuilderScope) {
            val list = StorageVersionMigrationListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun volumeAttachment(block: VolumeAttachmentDslBuilderScope) {
            val attachment = VolumeAttachmentDslBuilder().apply(block).build()
            resources.add(attachment)
        }

        fun volumeAttachmentList(block: VolumeAttachmentListDslBuilderScope) {
            val list = VolumeAttachmentListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun volumeAttributesClass(block: VolumeAttributesClassDslBuilderScope) {
            val volumeClass = VolumeAttributesClassDslBuilder().apply(block).build()
            resources.add(volumeClass)
        }

        fun volumeAttributesClassList(block: VolumeAttributesClassListDslBuilderScope) {
            val list = VolumeAttributesClassListDslBuilder().apply(block).build()
            lists.add(list)
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