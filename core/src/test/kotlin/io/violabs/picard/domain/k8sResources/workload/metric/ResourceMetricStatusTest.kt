package io.violabs.picard.domain.k8sResources.workload.metric


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