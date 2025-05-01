package io.violabs.picard.domain.k8sResources.workload.controllerRevision


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ControllerRevisionTest : FullBuildSim<ControllerRevision, ControllerRevision.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ControllerRevisionTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ControllerRevision, ControllerRevision.Builder> {
            scenario {
                id = "minimum"
                given(ControllerRevision.Builder()) {
                    revision = 1
                }
                expected = ControllerRevision(revision = 1)
            }

            scenario {
                id = "full"
                given(ControllerRevision.Builder()) {
                    sharedMetadata()
                    revision = 1
                    data {
                        PLACEHOLDER
                    }
                }
                expected = ControllerRevision(
                    metadata = METADATA,
                    revision = 1,
                    data = PLACEHOLDER
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ControllerRevision, ControllerRevision.Builder> {
            requireScenario("revision") {
                given(ControllerRevision.Builder())
            }
        }
    }
}