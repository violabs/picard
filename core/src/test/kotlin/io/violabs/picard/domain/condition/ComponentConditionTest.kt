package io.violabs.picard.domain.condition


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ComponentConditionTest : FailureBuildSim<ComponentCondition, ComponentCondition.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ComponentConditionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ComponentCondition, ComponentCondition.Builder> {
            requireScenario("status") {
                given(ComponentCondition.Builder())
            }

            requireScenario("type") {
                given(ComponentCondition.Builder()) {
                    status = BooleanType.True
                }
            }
        }
    }
}