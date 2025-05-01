package io.violabs.picard.domain.k8sResources.workload.podTemplate


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.workload.pod.Pod
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodTemplateTest : SuccessBuildSim<PodTemplate, PodTemplate.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodTemplateTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<PodTemplate, PodTemplate.Builder> {
            scenario {
                id = "minimum"
                given(PodTemplate.Builder())
                expected = PodTemplate()
            }

            scenario {
                id = "with spec"
                given(PodTemplate.Builder()) {
                    sharedMetadata()
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
                    spec = PodTemplate.Spec(
                        metadata = ObjectMetadata(),
                        spec = Pod.Spec(
                            containers = listOf(STANDARD_CONTAINER)
                        )
                    )
                )
            }
        }
    }
}