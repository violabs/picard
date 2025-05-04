package io.violabs.picard.domain.k8sResources.workload.controllerRevision


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ControllerRevisionListTest : FullBuildSim<ControllerRevisionList, ControllerRevisionList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ControllerRevisionListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ControllerRevisionList, ControllerRevisionList.Builder> {
            scenario {
                id = "minimum"
                given(ControllerRevisionList.Builder()) {
                    items {
                        controllerRevisionItem {
                            revision = 1
                        }
                    }
                }
                expected = ControllerRevisionList(
                    items = listOf(ControllerRevision(revision = 1))
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ControllerRevisionList, ControllerRevisionList.Builder> {
            requireNotEmptyScenario("items") {
                given(ControllerRevisionList.Builder())
            }
        }
    }
}