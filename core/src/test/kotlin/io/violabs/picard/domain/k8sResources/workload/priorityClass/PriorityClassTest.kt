package io.violabs.picard.domain.k8sResources.workload.priorityClass


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PriorityClassTest : FullBuildSim<PriorityClass, PriorityClassDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PriorityClassTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )
        
        private val SUCCESS_POSSIBILITIES = possibilities<PriorityClass, PriorityClassDslBuilder> {
            scenario {
                id = "minimum"
                given(PriorityClassDslBuilder()) {
                    value = 1
                }
                expected = PriorityClass(
                    value = 1
                )
            }

            scenario {
                id = "full"
                given(PriorityClassDslBuilder()) {
                    value = 1
                    description = PLACEHOLDER
                    globalDefault()
                    preemptionPolicy = PLACEHOLDER
                }
                expected = PriorityClass(
                    value = 1,
                    description = PLACEHOLDER,
                    globalDefault = true,
                    preemptionPolicy = PLACEHOLDER
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<PriorityClass, PriorityClassDslBuilder> {
            requireScenario("value") {
                given(PriorityClassDslBuilder())
            }
        }
    }
}