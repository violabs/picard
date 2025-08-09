package io.violabs.picard.v2.resources.cluster.service.api


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ApiServiceSpecTest : FailureBuildSim<ApiServiceSpec, ApiServiceSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ApiServiceSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ApiServiceSpec, ApiServiceSpecDslBuilder> {
            requireScenario("groupPriorityMinimum") {
                given(ApiServiceSpecDslBuilder())
            }

            requireScenario("versionPriority") {
                given(ApiServiceSpecDslBuilder()) {
                    groupPriorityMinimum = 1
                }
            }
        }
    }
}