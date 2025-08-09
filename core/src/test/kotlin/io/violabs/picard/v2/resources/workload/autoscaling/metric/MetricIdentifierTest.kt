package io.violabs.picard.v2.resources.workload.autoscaling.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class MetricIdentifierTest : FailureBuildSim<MetricIdentifier, MetricIdentifierDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            MetricIdentifierTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<MetricIdentifier, MetricIdentifierDslBuilder> {
            requireScenario("name") {
                given(MetricIdentifierDslBuilder())
            }
        }
    }
}