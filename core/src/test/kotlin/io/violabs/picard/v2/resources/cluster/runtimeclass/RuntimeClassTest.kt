package io.violabs.picard.v2.resources.cluster.runtimeclass

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class RuntimeClassTest : SuccessBuildSim<RuntimeClass, RuntimeClassV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RuntimeClassTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<RuntimeClass, RuntimeClassV2DslBuilder> {
            scenario {
                id = "minimum"
                given(RuntimeClassV2DslBuilder()) {
                    handler = PLACEHOLDER
                }
                expected = RuntimeClass(handler = PLACEHOLDER)
            }

            scenario {
                id = "full"
                given(RuntimeClassV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    handler = PLACEHOLDER
                    overhead {
                        podFixed(PLACEHOLDER to QUANTITY)
                    }
                    scheduling {
                        nodeSelector(PLACEHOLDER to PLACEHOLDER)
                        tolerations {
                            toleration {
                                key = PLACEHOLDER
                                operator = Toleration.Operator.EQUAL
                                value = PLACEHOLDER
                                effect = PLACEHOLDER
                                tolerationSeconds = 1
                            }
                        }
                    }
                }
                expected = RuntimeClass(
                    metadata = OBJECT_META,
                    handler = PLACEHOLDER,
                    overhead = Overhead(
                        podFixed = mapOf(PLACEHOLDER to QUANTITY)
                    ),
                    scheduling = Scheduling(
                        nodeSelector = mapOf(PLACEHOLDER to PLACEHOLDER),
                        tolerations = listOf(
                            Toleration(
                                key = PLACEHOLDER,
                                operator = Toleration.Operator.EQUAL,
                                value = PLACEHOLDER,
                                effect = PLACEHOLDER,
                                tolerationSeconds = 1
                            )
                        )
                    )
                )
            }
        }
    }
}