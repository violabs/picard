package io.violabs.picard.domain.k8sResources.policy.podDisruptionBudget


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodDisruptionBudgetSpecTest : SuccessBuildSim<PodDisruptionBudget.Spec, PodDisruptionBudget.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodDisruptionBudgetSpecTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<PodDisruptionBudget.Spec, PodDisruptionBudget.Spec.Builder> {
            scenario {
                id = "int or string"
                given(PodDisruptionBudget.Spec.Builder()) {
                    maxUnavailable("1")
                    minAvailable("1")
                }
                expected = PodDisruptionBudget.Spec(
                    maxUnavailable = INT_OR_STRING_2,
                    minAvailable = INT_OR_STRING_2
                )
            }
        }
    }
}