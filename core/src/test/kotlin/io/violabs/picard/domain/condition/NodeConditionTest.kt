package io.violabs.picard.domain.condition


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NodeConditionTest : FailureBuildSim<NodeCondition, NodeConditionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeConditionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<NodeCondition, NodeConditionDslBuilder> {
            requireScenario("status") {
                given(NodeConditionDslBuilder())
            }

            requireScenario("type") {
                given(NodeConditionDslBuilder()) {
                    status = BooleanType.True
                }
            }
        }
    }
}