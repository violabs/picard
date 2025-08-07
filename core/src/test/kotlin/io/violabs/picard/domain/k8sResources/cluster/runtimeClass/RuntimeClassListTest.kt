package io.violabs.picard.domain.k8sResources.cluster.runtimeClass


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class RuntimeClassListTest : FullBuildSim<RuntimeClassList, RuntimeClassListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RuntimeClassListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<RuntimeClassList, RuntimeClassListDslBuilder> {
            scenario {
                id = "minimum"
                given(RuntimeClassListDslBuilder()) {
                    items {
                        runtime {
                            handler = PLACEHOLDER
                        }
                    }
                }
                expected = RuntimeClassList(
                    items = listOf(RuntimeClass(handler = PLACEHOLDER))
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<RuntimeClassList, RuntimeClassListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(RuntimeClassListDslBuilder())
            }
        }
    }
}