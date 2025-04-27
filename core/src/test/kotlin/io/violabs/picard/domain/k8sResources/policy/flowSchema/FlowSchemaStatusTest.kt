package io.violabs.picard.domain.k8sResources.policy.flowSchema


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class FlowSchemaStatusTest : FailureBuildSim<FlowSchema.Status, FlowSchema.Status.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            FlowSchemaStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<FlowSchema.Status, FlowSchema.Status.Builder> {
            requireNotEmptyScenario("conditions") {
                given(FlowSchema.Status.Builder())
            }
        }
    }
}