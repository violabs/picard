package io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PriorityLevelConfigurationSpecTest :
    FailureBuildSim<PriorityLevelConfiguration.Spec, PriorityLevelConfiguration.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PriorityLevelConfigurationSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<PriorityLevelConfiguration.Spec, PriorityLevelConfiguration.Spec.Builder> {
                requireScenario("type") {
                    given(PriorityLevelConfiguration.Spec.Builder())
                }
            }
    }
}