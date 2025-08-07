package io.violabs.picard.domain.condition


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceConditionTest : FailureBuildSim<ServiceCondition, ServiceConditionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceConditionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ServiceCondition, ServiceConditionDslBuilder> {
            requireScenario("status") {
                given(ServiceConditionDslBuilder())
            }

            requireScenario("type") {
                given(ServiceConditionDslBuilder()) {
                    status = BooleanType.True
                }
            }
        }
    }
}