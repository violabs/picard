package io.violabs.picard.domain.k8sResources.workload.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ContainerResourceMetricStatusTest :
    FailureBuildSim<ContainerResourceMetricStatus, ContainerResourceMetricStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ContainerResourceMetricStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<ContainerResourceMetricStatus, ContainerResourceMetricStatusDslBuilder> {
                requireScenario("container") {
                    given(ContainerResourceMetricStatusDslBuilder())
                }

                requireScenario("current") {
                    given(ContainerResourceMetricStatusDslBuilder()) {
                        container = PLACEHOLDER
                    }
                }

                requireScenario("name") {
                    given(ContainerResourceMetricStatusDslBuilder()) {
                        container = PLACEHOLDER
                        current {

                        }
                    }
                }
            }
    }
}