package io.violabs.picard.v2.resources.policy.schema.flow


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class FlowSchemaListTest : FullBuildSim<FlowSchemaList, FlowSchemaListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            FlowSchemaListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<FlowSchemaList, FlowSchemaListDslBuilder> {
            scenario {
                id = "minimum"
                given(FlowSchemaListDslBuilder()) {
                    items {
                        flowSchema {  }
                    }
                }
                expected = FlowSchemaList(
                    items = listOf(
                        FlowSchema()
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<FlowSchemaList, FlowSchemaListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(FlowSchemaListDslBuilder())
            }
        }
    }
}