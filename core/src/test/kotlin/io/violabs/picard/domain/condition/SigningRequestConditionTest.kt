package io.violabs.picard.domain.condition


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SigningRequestConditionTest : FailureBuildSim<SigningRequestCondition, SigningRequestConditionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SigningRequestConditionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<SigningRequestCondition, SigningRequestConditionDslBuilder> {
            requireScenario("status") {
                given(SigningRequestConditionDslBuilder())
            }

            requireScenario("type") {
                given(SigningRequestConditionDslBuilder()) {
                    status = BooleanType.True
                }
            }
        }
    }
}