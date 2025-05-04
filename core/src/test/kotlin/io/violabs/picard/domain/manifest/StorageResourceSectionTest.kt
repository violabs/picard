package io.violabs.picard.domain.manifest


import io.violabs.picard.FullBuildSim
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
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StorageResourceSectionTest : FullBuildSim<StorageResourceSection, StorageResourceSection.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StorageResourceSectionTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val CSI_STORAGE_CAPACITY = CSIStorageCapacity(
            storageClassName = PLACEHOLDER
        )

        private val STORAGE_CLASS = StorageClass(
            provisioner = PLACEHOLDER
        )

        private val SUCCESS_POSSIBILITIES = possibilities<StorageResourceSection, StorageResourceSection.Builder> {
            scenario {
                id = "full"
                given(StorageResourceSection.Builder()) {
                    csiDriver {  }

                    csiDriverList {
                        items {
                            csiDriverItem {  }
                        }
                    }

                    csiNode {  }

                    csiNodeList {
                        items {
                            csiNodeItem {  }
                        }
                    }

                    csiStorageCapacity {
                        storageClassName = PLACEHOLDER
                    }

                    csiStorageCapacityList {
                        items {
                            csiStorageCapacityItem {
                                storageClassName = PLACEHOLDER
                            }
                        }
                    }

                    persistentVolume {  }

                    persistentVolumeList {
                        items {
                            persistentVolumeItem {  }
                        }
                    }

                    persistentVolumeClaim {  }

                    persistentVolumeClaimList {
                        items {
                            persistentVolumeClaimItem {  }
                        }
                    }

                    storageClass {
                        provisioner = PLACEHOLDER
                    }

                    storageClassList {
                        items {
                            storageClassItem {
                                provisioner = PLACEHOLDER
                            }
                        }
                    }

                    storageVersionMigration {  }

                    storageVersionMigrationList {
                        items {
                            storageVersionMigrationItem {  }
                        }
                    }

                    volumeAttachment {  }

                    volumeAttachmentList {
                        items {
                            volumeAttachmentItem {  }
                        }
                    }

                    volumeAttributesClass {  }

                    volumeAttributesClassList {
                        items {
                            volumeAttributesClassItem {  }
                        }
                    }
                }
                expected = StorageResourceSection(
                    resources = listOf(
                        CSIDriver(),
                        CSIDriverList(items = listOf(CSIDriver())),
                        CSINode(),
                        CSINodeList(items = listOf(CSINode())),
                        CSI_STORAGE_CAPACITY,
                        CSIStorageCapacityList(items = listOf(CSI_STORAGE_CAPACITY)),
                        PersistentVolume(),
                        PersistentVolumeClaim(),
                        PersistentVolumeClaimList(items = listOf(PersistentVolumeClaim())),
                        PersistentVolumeList(items = listOf(PersistentVolume())),
                        STORAGE_CLASS,
                        StorageClassList(items = listOf(STORAGE_CLASS)),
                        StorageVersionMigration(),
                        StorageVersionMigrationList(items = listOf(StorageVersionMigration())),
                        VolumeAttachment(),
                        VolumeAttachmentList(items = listOf(VolumeAttachment())),
                        VolumeAttributesClass(),
                        VolumeAttributesClassList(items = listOf(VolumeAttributesClass()))
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<StorageResourceSection, StorageResourceSection.Builder> {
            requireNotEmptyScenario("resources") {
                given(StorageResourceSection.Builder())
            }
        }
    }
}