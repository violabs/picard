package io.violabs.picard.domain.k8sResources.policy


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LimitResponseTest : FailureBuildSim<LimitResponse, LimitResponseDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LimitResponseTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<LimitResponse, LimitResponseDslBuilder> {
            requireScenario("type") {
                given(LimitResponseDslBuilder())
            }
        }
    }
}