package io.violabs.picard.domain.k8sResources.workload.job


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class OnPodConditionsPatternTest : FailureBuildSim<OnPodConditionsPattern, OnPodConditionsPattern.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            OnPodConditionsPatternTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<OnPodConditionsPattern, OnPodConditionsPattern.Builder> {
            requireScenario("status") {
                given(OnPodConditionsPattern.Builder())
            }

            requireScenario("type") {
                given(OnPodConditionsPattern.Builder()) {
                    status = BooleanType.True
                }
            }
        }
    }
}