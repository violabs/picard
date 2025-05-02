package io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.storage.volume.VolumeResourceRequirements
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.ModifyVolumeStatus
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PersistentVolumeClaimTest : SuccessBuildSim<PersistentVolumeClaim, PersistentVolumeClaim.Builder>() {
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

        private val SUCCESS_POSSIBILITIES = possibilities<PersistentVolumeClaim, PersistentVolumeClaim.Builder> {
            scenario {
                id = "minimum"
                given(PersistentVolumeClaim.Builder())
                expected = PersistentVolumeClaim()
            }

            scenario {
                id = "full"
                given(PersistentVolumeClaim.Builder()) {
                    sharedMetadata()
                    spec {
                        accessModes = PLACEHOLDER
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
                        accessModes = PLACEHOLDER
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
                    spec = PersistentVolumeClaim.Spec(
                        accessModes = PLACEHOLDER,
                        selector = LABEL_SELECTOR,
                        resources = VOLUME_RESOURCE_REQUIREMENTS,
                        storageClassName = PLACEHOLDER,
                        volumeName = PLACEHOLDER,
                        volumeMode = PLACEHOLDER
                    ),
                    status = PersistentVolumeClaim.Status(
                        accessModes = PLACEHOLDER,
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