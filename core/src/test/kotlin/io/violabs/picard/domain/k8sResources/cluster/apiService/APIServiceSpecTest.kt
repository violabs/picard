package io.violabs.picard.domain.k8sResources.cluster.apiService


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class APIServiceSpecTest : FailureBuildSim<APIService.Spec, APIService.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            APIServiceSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<APIService.Spec, APIService.Spec.Builder> {
            requireScenario("groupPriorityMinimum") {
                given(APIService.Spec.Builder())
            }

            requireScenario("versionPriority") {
                given(APIService.Spec.Builder()) {
                    groupPriorityMinimum = 1
                }
            }
        }
    }
}