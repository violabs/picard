package io.violabs.picard.domain.condition

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ConditionTest : FailureBuildSim<Condition, Condition.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ConditionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<Condition, Condition.Builder> {
            requireScenario("status") {
                given(Condition.Builder())
            }

            requireScenario("type") {
                given(Condition.Builder()) {
                    status = BooleanType.True
                }
            }
        }
    }
}