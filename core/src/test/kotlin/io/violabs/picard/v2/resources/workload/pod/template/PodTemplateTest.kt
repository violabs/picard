package io.violabs.picard.v2.resources.workload.pod.template


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.workload.pod.PodSpec
import org.junit.jupiter.api.BeforeAll

class PodTemplateTest : SuccessBuildSim<PodTemplate, PodTemplateDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodTemplateTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<PodTemplate, PodTemplateDslBuilder> {
            scenario {
                id = "minimum"
                given(PodTemplateDslBuilder())
                expected = PodTemplate()
            }

            scenario {
                id = "with spec"
                given(PodTemplateDslBuilder()) {
                    metadata { sharedObjectMeta() }
                    spec {
                        metadata { }
                        spec {
                            containers {
                                container {
                                    name = PLACEHOLDER
                                }
                            }
                        }
                    }
                }
                expected = PodTemplate(
                    metadata = METADATA,
                    spec = PodTemplateSpec(
                        metadata = Common.OBJECT_META,
                        spec = PodSpec(
                            containers = listOf(STANDARD_CONTAINER)
                        )
                    )
                )
            }
        }
    }
}