package io.violabs.picard.domain.k8sResources.workload.pod


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.K8sAnnotation
import io.violabs.picard.domain.Label
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodTest : SuccessBuildSim<Pod, Pod.Builder>() {
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

private val SUCCESS_POSSIBILITIES = possibilities<Pod, Pod.Builder> {
    scenario {
        id = "minimum"
        given(Pod.Builder())
        expected = Pod()
    }

    scenario {
        id = "full"
        given(Pod.Builder()) {
            metadata {
                name = "test"
                namespace = "test"
                generatedName = "test2"
                labels {
                    label("app", "test")
                }
                annotations {
                    annotations("test-run", "example")
                }
            }
        }
        expected = Pod(
            metadata = METADATA
        )
    }
}