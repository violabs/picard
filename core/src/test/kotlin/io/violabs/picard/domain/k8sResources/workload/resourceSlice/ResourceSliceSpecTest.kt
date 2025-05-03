package io.violabs.picard.domain.k8sResources.workload.resourceSlice


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceSliceSpecTest : FailureBuildSim<ResourceSlice.Spec, ResourceSlice.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceSliceSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ResourceSlice.Spec, ResourceSlice.Spec.Builder> {
            requireScenario("driver") {
                given(ResourceSlice.Spec.Builder())
            }

            requireScenario("pool") {
                given(ResourceSlice.Spec.Builder()) {
                    driver = PLACEHOLDER
                }
            }
        }
    }
}