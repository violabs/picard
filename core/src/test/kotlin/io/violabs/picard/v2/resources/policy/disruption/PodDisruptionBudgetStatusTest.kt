package io.violabs.picard.v2.resources.policy.disruption


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodDisruptionBudgetStatusTest : FailureBuildSim<PodDisruptionBudgetStatus, PodDisruptionBudgetStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodDisruptionBudgetStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<PodDisruptionBudgetStatus, PodDisruptionBudgetStatusDslBuilder> {
                requireScenario("currentHealthy") {
                    given(PodDisruptionBudgetStatusDslBuilder())
                }

                requireScenario("desiredHealthy") {
                    given(PodDisruptionBudgetStatusDslBuilder()) {
                        currentHealthy = 1
                    }
                }

                requireScenario("disruptionsAllowed") {
                    given(PodDisruptionBudgetStatusDslBuilder()) {
                        currentHealthy = 1
                        desiredHealthy = 1
                    }
                }

                requireScenario("expectedPods") {
                    given(PodDisruptionBudgetStatusDslBuilder()) {
                        currentHealthy = 1
                        desiredHealthy = 1
                        disruptionsAllowed = 1
                    }
                }
            }
    }
}