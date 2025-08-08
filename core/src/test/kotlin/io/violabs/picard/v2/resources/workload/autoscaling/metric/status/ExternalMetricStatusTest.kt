package io.violabs.picard.v2.resources.workload.autoscaling.metric.status


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ExternalMetricStatusTest : FailureBuildSim<ExternalMetricStatus, ExternalMetricStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ExternalMetricStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ExternalMetricStatus, ExternalMetricStatusDslBuilder> {
            requireScenario("current") {
                given(ExternalMetricStatusDslBuilder())
            }

            requireScenario("metric") {
                given(ExternalMetricStatusDslBuilder()) {
                    current {}
                }
            }
        }
    }
}