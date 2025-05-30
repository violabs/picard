package io.violabs.picard.domain.k8sResources.cluster.runtimeClass


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.Toleration
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class RuntimeClassTest : FullBuildSim<RuntimeClass, RuntimeClass.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RuntimeClassTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<RuntimeClass, RuntimeClass.Builder> {
            scenario {
                id = "minimum"
                given(RuntimeClass.Builder()) {
                    handler = PLACEHOLDER
                }
                expected = RuntimeClass(handler = PLACEHOLDER)
            }

            scenario {
                id = "full"
                given(RuntimeClass.Builder()) {
                    sharedMetadata()
                    handler = PLACEHOLDER
                    overhead {
                        podFixed(PLACEHOLDER to QUANTITY)
                    }
                    scheduling {
                        nodeSelector(PLACEHOLDER to PLACEHOLDER)
                        tolerations {
                            addToleration {
                                key = PLACEHOLDER
                                operator = PLACEHOLDER
                                value = PLACEHOLDER
                                effect = PLACEHOLDER
                                tolerationSeconds = 1
                            }
                        }
                    }
                }
                expected = RuntimeClass(
                    metadata = METADATA,
                    handler = PLACEHOLDER,
                    overhead = RuntimeClassOverhead(
                        podFixed = mapOf(PLACEHOLDER to QUANTITY)
                    ),
                    scheduling = RuntimeClassScheduling(
                        nodeSelector = mapOf(PLACEHOLDER to PLACEHOLDER),
                        tolerations = listOf(
                            Toleration(
                                key = PLACEHOLDER,
                                operator = PLACEHOLDER,
                                value = PLACEHOLDER,
                                effect = PLACEHOLDER,
                                tolerationSeconds = 1
                            )
                        )
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<RuntimeClass, RuntimeClass.Builder> {
            requireScenario("handler") {
                given(RuntimeClass.Builder())
            }
        }
    }
}