package io.violabs.picard.v2.resources.authorization.role.binding

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class RoleRefTest : FailureBuildSim<RoleRef, RoleRefDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RoleRefTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<RoleRef, RoleRefDslBuilder> {
            requireScenario("apiGroup") {
                given(RoleRefDslBuilder())
            }

            requireScenario("kind") {
                given(RoleRefDslBuilder()) {
                    apiGroup = PLACEHOLDER
                }
            }

            requireScenario("name") {
                given(RoleRefDslBuilder()) {
                    apiGroup = PLACEHOLDER
                    kind = PLACEHOLDER
                }
            }
        }
    }
}