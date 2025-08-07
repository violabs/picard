package io.violabs.picard.domain.k8sResources.workload.pod.resource


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceHealthTest : FailureBuildSim<ResourceHealth, ResourceHealthDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceHealthTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ResourceHealth, ResourceHealthDslBuilder> {
            requireScenario("resourceID") {
                given(ResourceHealthDslBuilder())
            }
        }
    }
}