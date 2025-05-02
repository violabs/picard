package io.violabs.picard.domain.k8sResources.workload.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class MetricTargetTest : FailureBuildSim<MetricTarget, MetricTarget.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            MetricTargetTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<MetricTarget, MetricTarget.Builder> {
            requireScenario("type") {
                given(MetricTarget.Builder())
            }
        }
    }
}