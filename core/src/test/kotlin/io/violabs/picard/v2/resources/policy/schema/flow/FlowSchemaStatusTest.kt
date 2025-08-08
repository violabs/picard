package io.violabs.picard.v2.resources.policy.schema.flow


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class FlowSchemaStatusTest : FailureBuildSim<FlowSchemaStatus, FlowSchemaStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            FlowSchemaStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<FlowSchemaStatus, FlowSchemaStatusDslBuilder> {
            requireNotEmptyScenario("conditions") {
                given(FlowSchemaStatusDslBuilder())
            }
        }
    }
}