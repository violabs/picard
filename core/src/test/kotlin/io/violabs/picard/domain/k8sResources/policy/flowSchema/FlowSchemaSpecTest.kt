package io.violabs.picard.domain.k8sResources.policy.flowSchema


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class FlowSchemaSpecTest : FailureBuildSim<FlowSchema.Spec, FlowSchema.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            FlowSchemaSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<FlowSchema.Spec, FlowSchema.Spec.Builder> {
            requireScenario("priorityLevelConfiguration") {
                given(FlowSchema.Spec.Builder())
            }
        }
    }
}