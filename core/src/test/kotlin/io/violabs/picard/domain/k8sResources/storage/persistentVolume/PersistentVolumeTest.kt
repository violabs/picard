package io.violabs.picard.domain.k8sResources.storage.persistentVolume


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PersistentVolumeTest : SuccessBuildSim<PersistentVolume, PersistentVolume.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PersistentVolumeTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val NODE_AFFINITY = VolumeNodeAffinity(
            required = NODE_SELECTOR
        )

        private val SUCCESS_POSSIBILITIES = possibilities<PersistentVolume, PersistentVolume.Builder> {
            scenario {
                id = "minimum"
                given(PersistentVolume.Builder())
                expected = PersistentVolume()
            }

            scenario {
                id = "minimum"
                given(PersistentVolume.Builder()) {
                    sharedMetadata()
                    spec {
                        accessModes = PLACEHOLDER
                        capacity(PLACEHOLDER to QUANTITY)
                        claimRef { sharedObjectReference() }
                        mountOptions(PLACEHOLDER)
                        nodeAffinity {
                            required {
                                this.terms {
                                    term {
                                        sharedNodeSelectorTerm()
                                    }
                                }
                            }
                        }
                        persistentVolumeReclaimPolicy = PLACEHOLDER
                        storageClassName = PLACEHOLDER
                        volumeAttributesClassName = PLACEHOLDER
                        volumeMode = PLACEHOLDER
                    }
                    this.status {
                        phase = PLACEHOLDER
                        message = PLACEHOLDER
                        reason = PLACEHOLDER
                        lastPhaseTransitionTime = NOW
                    }
                }
                expected = PersistentVolume(
                    metadata = METADATA,
                    spec = PersistentVolume.Spec(
                        accessModes = PLACEHOLDER,
                        capacity = QUANTITY_MAP,
                        claimRef = OBJECT_REFERENCE,
                        mountOptions = PLACEHOLDER_LIST,
                        nodeAffinity = NODE_AFFINITY,
                        persistentVolumeReclaimPolicy = PLACEHOLDER,
                        storageClassName = PLACEHOLDER,
                        volumeAttributesClassName = PLACEHOLDER,
                        volumeMode = PLACEHOLDER
                    ),
                    status = PersistentVolume.Status(
                        phase = PLACEHOLDER,
                        message = PLACEHOLDER,
                        reason = PLACEHOLDER,
                        lastPhaseTransitionTime = NOW
                    )
                )
            }
        }
    }
}