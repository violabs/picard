package io.violabs.picard.v2.resources.workload.autoscaling.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class MetricTargetTest : FailureBuildSim<MetricTarget, MetricTargetDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            MetricTargetTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<MetricTarget, MetricTargetDslBuilder> {
            requireScenario("type") {
                given(MetricTargetDslBuilder())
            }
        }
    }
}