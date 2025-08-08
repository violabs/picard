package io.violabs.picard.v2.resources.policy.limit


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LimitRangeSpecTest : FailureBuildSim<LimitRangeSpec, LimitRangeSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LimitRangeSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<LimitRangeSpec, LimitRangeSpecDslBuilder> {
            requireNotEmptyScenario("limits") {
                given(LimitRangeSpecDslBuilder())
            }
        }
    }
}