package io.violabs.picard.domain.k8sResources.workload.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodsMetricSourceTest : FailureBuildSim<PodsMetricSource, PodsMetricSource.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodsMetricSourceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<PodsMetricSource, PodsMetricSource.Builder> {
            requireScenario("metric") {
                given(PodsMetricSource.Builder())
            }

            requireScenario("target") {
                given(PodsMetricSource.Builder()) {
                    metric {
                        name = PLACEHOLDER
                    }
                }
            }
        }
    }
}