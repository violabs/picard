package io.violabs.picard.domain.v2.resources.storage.persistent.volume.claim


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.Common.sharedSelector
import io.violabs.picard.Conditions
import io.violabs.picard.Conditions.sharedPersistentVolumeClaimCondition
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.AccessMode
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.configstorage.persistent.volume.claim.ModifyVolumeStatus
import io.violabs.picard.v2.resources.configstorage.persistent.volume.claim.PersistentVolumeClaimV2
import io.violabs.picard.v2.resources.configstorage.persistent.volume.claim.PersistentVolumeClaimV2DslBuilder
import io.violabs.picard.v2.resources.configstorage.persistent.volume.claim.VolumeResourceRequirements
import org.junit.jupiter.api.BeforeAll

class PersistentVolumeClaimTest : SuccessBuildSim<PersistentVolumeClaimV2, PersistentVolumeClaimV2DslBuilder>() {
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

        private val SUCCESS_POSSIBILITIES = possibilities<PersistentVolumeClaimV2, PersistentVolumeClaimV2DslBuilder> {
            scenario {
                id = "minimum"
                given(PersistentVolumeClaimV2DslBuilder())
                expected = PersistentVolumeClaimV2()
            }

            scenario {
                id = "full"
                given(PersistentVolumeClaimV2DslBuilder()) {
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
                expected = PersistentVolumeClaimV2(
                    metadata = Common.OBJECT_META,
                    spec = PersistentVolumeClaimV2.Spec(
                        accessModes = listOf(AccessMode.ReadWriteOnce),
                        selector = Common.LABEL_SELECTOR,
                        resources = VOLUME_RESOURCE_REQUIREMENTS,
                        storageClassName = PLACEHOLDER,
                        volumeName = PLACEHOLDER,
                        volumeMode = PLACEHOLDER
                    ),
                    status = PersistentVolumeClaimV2.Status(
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