package io.violabs.picard.v2.resources.workload.scheduling

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PriorityClassTest : SuccessBuildSim<PriorityClass, PriorityClassV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PriorityClassTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<PriorityClass, PriorityClassV2DslBuilder> {
            scenario {
                id = "minimum"
                given(PriorityClassV2DslBuilder()) {
                    value = 1
                }
                expected = PriorityClass(
                    value = 1
                )
            }

            scenario {
                id = "full"
                given(PriorityClassV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    value = 1000
                    description = PLACEHOLDER
                    globalDefault(true)
                    preemptionPolicy = PriorityClass.PreemptionPolicy.PreemptLowerPriority
                }
                expected = PriorityClass(
                    metadata = OBJECT_META,
                    value = 1000,
                    description = PLACEHOLDER,
                    globalDefault = true,
                    preemptionPolicy = PriorityClass.PreemptionPolicy.PreemptLowerPriority
                )
            }
        }
    }
}