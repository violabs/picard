package io.violabs.picard.domain.k8sResources.workload.controllerRevision


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ControllerRevisionTest : FullBuildSim<ControllerRevision, ControllerRevisionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ControllerRevisionTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ControllerRevision, ControllerRevisionDslBuilder> {
            scenario {
                id = "minimum"
                given(ControllerRevisionDslBuilder()) {
                    revision = 1
                }
                expected = ControllerRevision(revision = 1)
            }

            scenario {
                id = "full"
                given(ControllerRevisionDslBuilder()) {
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

        private val FAILURE_POSSIBILITIES = possibilities<ControllerRevision, ControllerRevisionDslBuilder> {
            requireScenario("revision") {
                given(ControllerRevisionDslBuilder())
            }
        }
    }
}