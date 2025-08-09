package io.violabs.picard.v2.resources.workload.autoscaling.metric.status


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceMetricStatusTest : FailureBuildSim<ResourceMetricStatus, ResourceMetricStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceMetricStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ResourceMetricStatus, ResourceMetricStatusDslBuilder> {
            requireScenario("current") {
                given(ResourceMetricStatusDslBuilder())
            }

            requireScenario("name") {
                given(ResourceMetricStatusDslBuilder()) {
                    current {  }
                }
            }
        }
    }
}