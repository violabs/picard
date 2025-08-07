package io.violabs.picard.domain.k8sResources.workload.pod


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.K8sAnnotation
import io.violabs.picard.domain.label.Label
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.workload.pod.container.Container
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodTest : SuccessBuildSim<Pod, PodDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodTest::class,
            SUCCESS_POSSIBILITIES
        )
    }
}

private val METADATA = ObjectMetadata(
    name = "test",
    namespace = "test",
    generatedName = "test2",
    labels = listOf(Label("app", "test")),
    annotations = listOf(K8sAnnotation("test-run", "example")),
)

private val SUCCESS_POSSIBILITIES = possibilities<Pod, PodDslBuilder> {
    scenario {
        id = "minimum"
        given(PodDslBuilder())
        expected = Pod()
    }

    scenario {
        id = "full metadata"
        description = "full metadata with placeholders for minimum Spec and Status"
        given(PodDslBuilder()) {
            metadata {
                name = "test"
                namespace = "test"
                generatedName = "test2"
                labels {
                    label("app", "test")
                }
                annotations {
                    add("test-run", "example")
                }
            }

            spec {
                containers {
                    container { name = "test" }
                }
            }

            this.status {  }
        }
        expected = Pod(
            metadata = METADATA,
            spec = PodSpec(
                containers = listOf(Container("test"))
            ),
            status = PodStatus()
        )
    }
}