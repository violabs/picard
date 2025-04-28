package io.violabs.picard.domain.k8sResources.policy.podDisruptionBudget


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodDisruptionBudgetStatusTest : FailureBuildSim<PodDisruptionBudget.Status, PodDisruptionBudget.Status.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodDisruptionBudgetStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<PodDisruptionBudget.Status, PodDisruptionBudget.Status.Builder> {
                requireScenario("currentHealthy") {
                    given(PodDisruptionBudget.Status.Builder())
                }

                requireScenario("desiredHealthy") {
                    given(PodDisruptionBudget.Status.Builder()) {
                        currentHealthy = 1
                    }
                }

                requireScenario("disruptionsAllowed") {
                    given(PodDisruptionBudget.Status.Builder()) {
                        currentHealthy = 1
                        desiredHealthy = 1
                    }
                }

                requireScenario("expectedPods") {
                    given(PodDisruptionBudget.Status.Builder()) {
                        currentHealthy = 1
                        desiredHealthy = 1
                        disruptionsAllowed = 1
                    }
                }
            }
    }
}