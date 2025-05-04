package io.violabs.picard.domain.k8sResources.workload.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class MetricSpecTest : FailureBuildSim<MetricSpec, MetricSpec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            MetricSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<MetricSpec, MetricSpec.Builder> {
            requireScenario("type") {
                given(MetricSpec.Builder())
            }
        }
    }
}