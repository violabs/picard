package io.violabs.picard.v2.resources.workload.autoscaling.metric.status


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class MetricStatusTest : FailureBuildSim<MetricStatus, MetricStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            MetricStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<MetricStatus, MetricStatusDslBuilder> {
            requireScenario("type") {
                given(MetricStatusDslBuilder())
            }
        }
    }
}