package io.violabs.picard.v2.resources.policy.disruption


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodDisruptionBudgetSpecTest : SuccessBuildSim<PodDisruptionBudgetSpec, PodDisruptionBudgetSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodDisruptionBudgetSpecTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<PodDisruptionBudgetSpec, PodDisruptionBudgetSpecDslBuilder> {
            scenario {
                id = "int or string"
                given(PodDisruptionBudgetSpecDslBuilder()) {
                    maxUnavailable = INT_OR_STRING_1
                    minAvailable = INT_OR_STRING_1
                }
                expected = PodDisruptionBudgetSpec(
                    maxUnavailable = INT_OR_STRING_1,
                    minAvailable = INT_OR_STRING_1
                )
            }
        }
    }
}