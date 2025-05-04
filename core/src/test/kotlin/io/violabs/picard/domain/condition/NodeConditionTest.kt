package io.violabs.picard.domain.condition


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NodeConditionTest : FailureBuildSim<NodeCondition, NodeCondition.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeConditionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<NodeCondition, NodeCondition.Builder> {
            requireScenario("status") {
                given(NodeCondition.Builder())
            }

            requireScenario("type") {
                given(NodeCondition.Builder()) {
                    status = BooleanType.True
                }
            }
        }
    }
}