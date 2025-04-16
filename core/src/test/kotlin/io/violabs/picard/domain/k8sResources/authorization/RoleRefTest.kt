package io.violabs.picard.domain.k8sResources.authorization


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class RoleRefTest : FailureBuildSim<RoleRef, RoleRef.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RoleRefTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<RoleRef, RoleRef.Builder> {
            requireScenario("apiGroup") {
                given(RoleRef.Builder())
            }

            requireScenario("kind") {
                given(RoleRef.Builder()) {
                    apiGroup = PLACEHOLDER
                }
            }

            requireScenario("name") {
                given(RoleRef.Builder()) {
                    apiGroup = PLACEHOLDER
                    kind = PLACEHOLDER
                }
            }
        }
    }
}