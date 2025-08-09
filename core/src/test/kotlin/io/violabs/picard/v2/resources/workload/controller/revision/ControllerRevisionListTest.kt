package io.violabs.picard.v2.resources.workload.controller.revision

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ControllerRevisionListTest : FullBuildSim<ControllerRevisionList, ControllerRevisionListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ControllerRevisionListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ControllerRevisionList, ControllerRevisionListDslBuilder> {
            scenario {
                id = "minimum"
                given(ControllerRevisionListDslBuilder()) {
                    items {
                        controllerRevision {
                            revision = 1
                        }
                    }
                }
                expected = ControllerRevisionList(
                    items = listOf(ControllerRevision(revision = 1))
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ControllerRevisionList, ControllerRevisionListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(ControllerRevisionListDslBuilder())
            }
        }
    }
}