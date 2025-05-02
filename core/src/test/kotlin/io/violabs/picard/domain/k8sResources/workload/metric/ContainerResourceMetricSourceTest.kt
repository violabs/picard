package io.violabs.picard.domain.k8sResources.workload.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ContainerResourceMetricSourceTest :
    FailureBuildSim<ContainerResourceMetricSource, ContainerResourceMetricSource.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ContainerResourceMetricSourceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<ContainerResourceMetricSource, ContainerResourceMetricSource.Builder> {
                requireScenario("container") {
                    given(ContainerResourceMetricSource.Builder())
                }

                requireScenario("name") {
                    given(ContainerResourceMetricSource.Builder()) {
                        container = PLACEHOLDER
                    }
                }
            }
    }
}