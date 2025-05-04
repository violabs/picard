package io.violabs.picard.domain.condition


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SigningRequestConditionTest : FailureBuildSim<SigningRequestCondition, SigningRequestCondition.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SigningRequestConditionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<SigningRequestCondition, SigningRequestCondition.Builder> {
            requireScenario("status") {
                given(SigningRequestCondition.Builder())
            }

            requireScenario("type") {
                given(SigningRequestCondition.Builder()) {
                    status = BooleanType.True
                }
            }
        }
    }
}