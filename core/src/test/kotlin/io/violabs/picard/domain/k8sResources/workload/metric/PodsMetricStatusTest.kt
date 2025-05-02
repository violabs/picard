package io.violabs.picard.domain.k8sResources.workload.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodsMetricStatusTest : FailureBuildSim<PodsMetricStatus, PodsMetricStatus.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodsMetricStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<PodsMetricStatus, PodsMetricStatus.Builder> {
            requireScenario("current") {
                given(PodsMetricStatus.Builder())
            }

            requireScenario("metric") {
                given(PodsMetricStatus.Builder()) {
                    current {

                    }
                }
            }

            requireScenario("resource") {
                given(PodsMetricStatus.Builder()) {
                    current {

                    }
                    metric {
                        name = PLACEHOLDER
                    }
                }
            }
        }
    }
}