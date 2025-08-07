package io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.AccessMode
import io.violabs.picard.domain.k8sResources.storage.volume.VolumeResourceRequirements
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.ModifyVolumeStatus
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PersistentVolumeClaimTest : SuccessBuildSim<PersistentVolumeClaim, PersistentVolumeClaimDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PersistentVolumeClaimTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val VOLUME_RESOURCE_REQUIREMENTS = VolumeResourceRequirements(
            requests = QUANTITY_MAP,
            limits = QUANTITY_MAP
        )

        private val SUCCESS_POSSIBILITIES = possibilities<PersistentVolumeClaim, PersistentVolumeClaimDslBuilder> {
            scenario {
                id = "minimum"
                given(PersistentVolumeClaimDslBuilder())
                expected = PersistentVolumeClaim()
            }

            scenario {
                id = "full"
                given(PersistentVolumeClaimDslBuilder()) {
                    sharedMetadata()
                    spec {
                        accessModes(AccessMode.ReadWriteOnce)
                        selector {
                            sharedSelector()
                        }
                        resources {
                            requests(PLACEHOLDER to PLACEHOLDER)
                            limits(PLACEHOLDER to PLACEHOLDER)
                        }
                        storageClassName = PLACEHOLDER
                        volumeName = PLACEHOLDER
                        volumeMode = PLACEHOLDER
                    }
                    this.status {
                        accessModes(AccessMode.ReadWriteOnce)
                        allocatedResourceStatuses(PLACEHOLDER to PLACEHOLDER)
                        allocatedResources(PLACEHOLDER to PLACEHOLDER)
                        capacity(PLACEHOLDER to PLACEHOLDER)
                        conditions {
                            sharedCondition()
                        }
                        currentVolumeAttributesClassName = PLACEHOLDER
                        modifyVolumeStatus {
                            status = ModifyVolumeStatus.Name.Pending
                            targetVolumeAttributesClassName = PLACEHOLDER
                        }
                        phase = PLACEHOLDER
                    }
                }
                expected = PersistentVolumeClaim(
                    metadata = METADATA,
                    spec = PersistentVolumeClaimSpec(
                        accessModes = listOf(AccessMode.ReadWriteOnce),
                        selector = LABEL_SELECTOR,
                        resources = VOLUME_RESOURCE_REQUIREMENTS,
                        storageClassName = PLACEHOLDER,
                        volumeName = PLACEHOLDER,
                        volumeMode = PLACEHOLDER
                    ),
                    status = PersistentVolumeClaimStatus(
                        accessModes = listOf(AccessMode.ReadWriteOnce),
                        allocatedResourceStatuses = STRING_MAP,
                        allocatedResources = QUANTITY_MAP,
                        capacity = QUANTITY_MAP,
                        conditions = listOf(CONDITION),
                        currentVolumeAttributesClassName = PLACEHOLDER,
                        modifyVolumeStatus = ModifyVolumeStatus(
                            status = ModifyVolumeStatus.Name.Pending.name,
                            targetVolumeAttributesClassName = PLACEHOLDER
                        ),
                        phase = PLACEHOLDER
                    )
                )
            }
        }
    }
}