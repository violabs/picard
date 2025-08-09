package io.violabs.picard.v2.resources.storage.persistent.volume.claim


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.Common.sharedSelector
import io.violabs.picard.Conditions
import io.violabs.picard.Conditions.sharedPersistentVolumeClaimCondition
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.AccessMode
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
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        accessModes(AccessMode.ReadWriteOnce)
                        selector {
                            sharedSelector()
                        }
                        resources {
                            requests(PLACEHOLDER to QUANTITY)
                            limits(PLACEHOLDER to QUANTITY)
                        }
                        storageClassName = PLACEHOLDER
                        volumeName = PLACEHOLDER
                        volumeMode = PLACEHOLDER
                    }

                    status {
                        accessModes(AccessMode.ReadWriteOnce)
                        allocatedResourceStatuses(PLACEHOLDER to PLACEHOLDER)
                        allocatedResources(PLACEHOLDER to QUANTITY)
                        capacity(PLACEHOLDER to QUANTITY)
                        conditions {
                            persistentVolumeClaimCondition {
                                sharedPersistentVolumeClaimCondition()
                            }
                        }
                        currentVolumeAttributesClassName = PLACEHOLDER
                        modifyVolumeStatus {
                            status = ModifyVolumeStatus.Type.Pending
                            targetVolumeAttributesClassName = PLACEHOLDER
                        }
                        phase = PLACEHOLDER
                    }
                }
                expected = PersistentVolumeClaim(
                    metadata = Common.OBJECT_META,
                    spec = PersistentVolumeClaimSpec(
                        accessModes = listOf(AccessMode.ReadWriteOnce),
                        selector = Common.LABEL_SELECTOR,
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
                        conditions = listOf(Conditions.PERSISTENT_VOLUME_CLAIM_CONDITION),
                        currentVolumeAttributesClassName = PLACEHOLDER,
                        modifyVolumeStatus = ModifyVolumeStatus(
                            status = ModifyVolumeStatus.Type.Pending,
                            targetVolumeAttributesClassName = PLACEHOLDER
                        ),
                        phase = PLACEHOLDER
                    )
                )
            }
        }
    }
}