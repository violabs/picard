package io.violabs.picard.domain.k8sResources.policy.flowSchema


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class FlowSchemaListTest : FullBuildSim<FlowSchemaList, FlowSchemaList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            FlowSchemaListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<FlowSchemaList, FlowSchemaList.Builder> {
            scenario {
                id = "minimum"
                given(FlowSchemaList.Builder()) {
                    items {
                        flowSchemaItem {  }
                    }
                }
                expected = FlowSchemaList(
                    items = listOf(
                        FlowSchema()
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<FlowSchemaList, FlowSchemaList.Builder> {
            requireNotEmptyScenario("items") {
                given(FlowSchemaList.Builder())
            }
        }
    }
}