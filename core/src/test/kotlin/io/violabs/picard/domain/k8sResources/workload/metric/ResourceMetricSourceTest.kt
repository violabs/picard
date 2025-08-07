package io.violabs.picard.domain.k8sResources.workload.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceMetricSourceTest : FailureBuildSim<ResourceMetricSource, ResourceMetricSourceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceMetricSourceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ResourceMetricSource, ResourceMetricSourceDslBuilder> {
            requireScenario("name") {
                given(ResourceMetricSourceDslBuilder())
            }
        }
    }
}