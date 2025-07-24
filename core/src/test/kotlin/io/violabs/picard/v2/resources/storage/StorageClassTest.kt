package io.violabs.picard.v2.resources.storage


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.storage.selector.label.TopologySelectorLabelRequirement
import io.violabs.picard.v2.resources.storage.selector.label.TopologySelectorTerm
import org.junit.jupiter.api.BeforeAll

class StorageClassTest : FullBuildSim<StorageClassV2, StorageClassV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StorageClassTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<StorageClassV2, StorageClassV2DslBuilder> {
            scenario {
                id = "minimum"
                given(StorageClassV2DslBuilder()) {
                    provisioner = PLACEHOLDER
                }
                expected = StorageClassV2(
                    provisioner = PLACEHOLDER
                )
            }

            scenario {
                id = "full"
                given(StorageClassV2DslBuilder()) {
                    provisioner = PLACEHOLDER
                    allowVolumeExpansion()
                    allowedTopologies {
                        topologySelectorTerm {
                            matchLabelExpressions {
                                topologySelectorLabelRequirement {
                                    key = PLACEHOLDER
                                    values(PLACEHOLDER)
                                }
                            }
                        }
                    }
                    mountOptions(PLACEHOLDER)
                    parameters(PLACEHOLDER to PLACEHOLDER)
                    reclaimPolicy = PLACEHOLDER
                    volumeBindingMode = PLACEHOLDER
                }
                expected = StorageClassV2(
                    provisioner = PLACEHOLDER,
                    allowVolumeExpansion = true,
                    allowedTopologies = listOf(
                        TopologySelectorTerm(
                            matchLabelExpressions = listOf(
                                TopologySelectorLabelRequirement(
                                    key = PLACEHOLDER,
                                    values = listOf(PLACEHOLDER)
                                )
                            )
                        )
                    ),
                    mountOptions = listOf(PLACEHOLDER),
                    parameters = mapOf(PLACEHOLDER to PLACEHOLDER),
                    reclaimPolicy = PLACEHOLDER,
                    volumeBindingMode = PLACEHOLDER
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<StorageClassV2, StorageClassV2DslBuilder> {
            requireScenario("provisioner") {
                given(StorageClassV2DslBuilder())
            }
        }
    }
}