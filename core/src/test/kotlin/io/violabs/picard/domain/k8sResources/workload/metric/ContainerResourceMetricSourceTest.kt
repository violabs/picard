package io.violabs.picard.domain.k8sResources.workload.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ContainerResourceMetricSourceTest :
    FailureBuildSim<ContainerResourceMetricSource, ContainerResourceMetricSourceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ContainerResourceMetricSourceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<ContainerResourceMetricSource, ContainerResourceMetricSourceDslBuilder> {
                requireScenario("container") {
                    given(ContainerResourceMetricSourceDslBuilder())
                }

                requireScenario("name") {
                    given(ContainerResourceMetricSourceDslBuilder()) {
                        container = PLACEHOLDER
                    }
                }
            }
    }
}