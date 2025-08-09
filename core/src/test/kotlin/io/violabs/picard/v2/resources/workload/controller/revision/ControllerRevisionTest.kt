package io.violabs.picard.v2.resources.workload.controller.revision

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ControllerRevisionTest : SuccessBuildSim<ControllerRevision, ControllerRevisionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ControllerRevisionTest::class,
            SUCCESS_POSSIBILITIES
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
                    metadata {
                        sharedObjectMeta()
                    }
                    revision = 1
                    data = PLACEHOLDER
                }
                expected = ControllerRevision(
                    metadata = OBJECT_META,
                    revision = 1,
                    data = PLACEHOLDER
                )
            }
        }
    }
}