package io.violabs.picard.domain.k8sResources.workload.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ExternalMetricSourceTest : FailureBuildSim<ExternalMetricSource, ExternalMetricSource.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ExternalMetricSourceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ExternalMetricSource, ExternalMetricSource.Builder> {
            requireScenario("metric") {
                given(ExternalMetricSource.Builder())
            }

            requireScenario("target") {
                given(ExternalMetricSource.Builder()) {
                    metric {
                        name = PLACEHOLDER
                    }
                }
            }
        }
    }
}