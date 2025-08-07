package io.violabs.picard.domain.k8sResources.policy.flowSchema


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class FlowSchemaSpecTest : FailureBuildSim<FlowSchemaSpec, FlowSchemaSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            FlowSchemaSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<FlowSchemaSpec, FlowSchemaSpecDslBuilder> {
            requireScenario("priorityLevelConfiguration") {
                given(FlowSchemaSpecDslBuilder())
            }
        }
    }
}