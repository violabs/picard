package io.violabs.picard.v2.resources.storage.persistent.volume


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.Common.sharedObjectReference
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.common.NodeSelectorRequirement
import org.junit.jupiter.api.BeforeAll

class PersistentVolumeTest : SuccessBuildSim<PersistentVolumeV2, PersistentVolumeV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PersistentVolumeTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<PersistentVolumeV2, PersistentVolumeV2DslBuilder> {
            scenario {
                id = "minimum"
                given(PersistentVolumeV2DslBuilder())
                expected = PersistentVolumeV2()
            }

            scenario {
                id = "minimum"
                given(PersistentVolumeV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        accessModes(PLACEHOLDER)
                        capacity(PLACEHOLDER to QUANTITY)
                        claimRef {
                            sharedObjectReference()
                        }
                        mountOptions(PLACEHOLDER)
                        nodeAffinity {
                            required {
                                nodeSelectorTerms {
                                    nodeSelectorTerm {
                                        matchExpressions {
                                            nodeSelectorRequirement {
                                                key = PLACEHOLDER
                                                operator = NodeSelectorRequirement.Operator.In
                                                values(PLACEHOLDER)
                                            }
                                        }
                                        matchFields {
                                            nodeSelectorRequirement {
                                                key = PLACEHOLDER
                                                operator = NodeSelectorRequirement.Operator.In
                                                values(PLACEHOLDER)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        persistentVolumeReclaimPolicy = PersistentVolumeReclaimPolicy.Retain
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
                expected = PersistentVolumeV2(
                    metadata = Common.OBJECT_META,
                    spec = PersistentVolumeSpec(
                        accessModes = listOf(PLACEHOLDER),
                        capacity = QUANTITY_MAP,
                        claimRef = Common.OBJECT_REFERENCE,
                        mountOptions = PLACEHOLDER_LIST,
                        nodeAffinity = VolumeNodeAffinity(
                            required = NodeSelector(
                                nodeSelectorTerms = listOf(
                                    NodeSelectorTerm(
                                        matchExpressions = listOf(
                                            NodeSelectorRequirement(
                                                key = PLACEHOLDER,
                                                operator = NodeSelectorRequirement.Operator.In,
                                                values = PLACEHOLDER_LIST
                                            )
                                        ),
                                        matchFields = listOf(
                                            NodeSelectorRequirement(
                                                key = PLACEHOLDER,
                                                operator = NodeSelectorRequirement.Operator.In,
                                                values = PLACEHOLDER_LIST
                                            )
                                        )
                                    )
                                )
                            )
                        ),
                        persistentVolumeReclaimPolicy = PersistentVolumeReclaimPolicy.Retain,
                        storageClassName = PLACEHOLDER,
                        volumeAttributesClassName = PLACEHOLDER,
                        volumeMode = PLACEHOLDER
                    ),
                    status = PersistentVolumeStatus(
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