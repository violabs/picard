package io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceClaimTemplateSpecTest : FailureBuildSim<ResourceClaimTemplateSpec, ResourceClaimTemplateSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceClaimTemplateSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<ResourceClaimTemplateSpec, ResourceClaimTemplateSpecDslBuilder> {
                requireScenario("spec") {
                    given(ResourceClaimTemplateSpecDslBuilder())
                }
            }
    }
}