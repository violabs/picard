package io.violabs.picard.domain.condition


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ComponentConditionTest : FailureBuildSim<ComponentCondition, ComponentConditionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ComponentConditionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ComponentCondition, ComponentConditionDslBuilder> {
            requireScenario("status") {
                given(ComponentConditionDslBuilder())
            }

            requireScenario("type") {
                given(ComponentConditionDslBuilder()) {
                    status = BooleanType.True
                }
            }
        }
    }
}