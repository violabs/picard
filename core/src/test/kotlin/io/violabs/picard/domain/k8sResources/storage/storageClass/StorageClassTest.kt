package io.violabs.picard.domain.k8sResources.storage.storageClass


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.TopologySelectorTerm
import io.violabs.picard.domain.TopologySelectorLabelRequirement
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StorageClassTest : FullBuildSim<StorageClass, StorageClass.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StorageClassTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<StorageClass, StorageClass.Builder> {
            scenario {
                id = "minimum"
                given(StorageClass.Builder()) {
                    provisioner = PLACEHOLDER
                }
                expected = StorageClass(
                    provisioner = PLACEHOLDER
                )
            }

            scenario {
                id = "full"
                given(StorageClass.Builder()) {
                    provisioner = PLACEHOLDER
                    allowVolumeExpansion()
                    allowedTopologies {
                        addTopologySelectorTerm {
                            matchLabelExpressions {
                                addTopologySelectorLabelRequirement {
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
                expected = StorageClass(
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

        private val FAILURE_POSSIBILITIES = possibilities<StorageClass, StorageClass.Builder> {
            requireScenario("provisioner") {
                given(StorageClass.Builder())
            }
        }
    }
}