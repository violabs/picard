package io.violabs.picard.domain.k8sResources.workload.priorityClass


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PriorityClassTest : FullBuildSim<PriorityClass, PriorityClass.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PriorityClassTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )
        
        private val SUCCESS_POSSIBILITIES = possibilities<PriorityClass, PriorityClass.Builder> {
            scenario {
                id = "minimum"
                given(PriorityClass.Builder()) {
                    value = 1
                }
                expected = PriorityClass(
                    value = 1
                )
            }

            scenario {
                id = "full"
                given(PriorityClass.Builder()) {
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

        private val FAILURE_POSSIBILITIES = possibilities<PriorityClass, PriorityClass.Builder> {
            requireScenario("value") {
                given(PriorityClass.Builder())
            }
        }
    }
}