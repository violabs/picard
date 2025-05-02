package io.violabs.picard.domain.k8sResources.workload.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ExternalMetricStatusTest : FailureBuildSim<ExternalMetricStatus, ExternalMetricStatus.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ExternalMetricStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ExternalMetricStatus, ExternalMetricStatus.Builder> {
            requireScenario("current") {
                given(ExternalMetricStatus.Builder())
            }

            requireScenario("metric") {
                given(ExternalMetricStatus.Builder()) {
                    current {}
                }
            }
        }
    }
}