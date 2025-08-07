package io.violabs.picard.domain.k8sResources.workload.metric


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