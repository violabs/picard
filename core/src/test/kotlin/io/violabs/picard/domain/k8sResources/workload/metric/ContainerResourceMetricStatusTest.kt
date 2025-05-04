package io.violabs.picard.domain.k8sResources.workload.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ContainerResourceMetricStatusTest :
    FailureBuildSim<ContainerResourceMetricStatus, ContainerResourceMetricStatus.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ContainerResourceMetricStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<ContainerResourceMetricStatus, ContainerResourceMetricStatus.Builder> {
                requireScenario("container") {
                    given(ContainerResourceMetricStatus.Builder())
                }

                requireScenario("current") {
                    given(ContainerResourceMetricStatus.Builder()) {
                        container = PLACEHOLDER
                    }
                }

                requireScenario("name") {
                    given(ContainerResourceMetricStatus.Builder()) {
                        container = PLACEHOLDER
                        current {

                        }
                    }
                }
            }
    }
}