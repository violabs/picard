package io.violabs.picard.domain.k8sResources.workload.podTemplate


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.workload.pod.Pod
import io.violabs.picard.possibilities
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
                    sharedMetadata()
                    spec {
                        metadata { }
                        templateSpec {
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
                        metadata = ObjectMetadata(),
                        spec = PodSpec(
                            containers = listOf(STANDARD_CONTAINER)
                        )
                    )
                )
            }
        }
    }
}