package io.violabs.picard.v2.resources.workload.pod

import io.violabs.picard.BuildSim.Companion.PLACEHOLDER
import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.workload.pod.container.Container
import org.junit.jupiter.api.BeforeAll

class PodTest : SuccessBuildSim<PodV2, PodV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<PodV2, PodV2DslBuilder> {
            scenario {
                id = "minimum"
                given(PodV2DslBuilder())
                expected = PodV2()
            }

            scenario {
                id = "full metadata"
                description = "full metadata with placeholders for minimum Spec and Status"
                given(PodV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        containers {
                            container { 
                                name = PLACEHOLDER
                            }
                        }
                    }

                    status { }
                }
                expected = PodV2(
                    metadata = OBJECT_META,
                    spec = PodSpec(
                        containers = listOf(Container(name = PLACEHOLDER))
                    ),
                    status = PodStatus()
                )
            }
        }
    }
}