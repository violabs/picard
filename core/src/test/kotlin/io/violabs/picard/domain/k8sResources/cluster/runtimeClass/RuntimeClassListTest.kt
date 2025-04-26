package io.violabs.picard.domain.k8sResources.cluster.runtimeClass


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class RuntimeClassListTest : FullBuildSim<RuntimeClassList, RuntimeClassList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RuntimeClassListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<RuntimeClassList, RuntimeClassList.Builder> {
            scenario {
                id = "minimum"
                given(RuntimeClassList.Builder()) {
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

        private val FAILURE_POSSIBILITIES = possibilities<RuntimeClassList, RuntimeClassList.Builder> {
            requireNotEmptyScenario("items") {
                given(RuntimeClassList.Builder())
            }
        }
    }
}