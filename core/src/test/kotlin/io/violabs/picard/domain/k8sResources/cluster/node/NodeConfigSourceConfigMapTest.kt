package io.violabs.picard.domain.k8sResources.cluster.node


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NodeConfigSourceConfigMapTest : FailureBuildSim<NodeConfigSourceConfigMap, NodeConfigSourceConfigMapDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeConfigSourceConfigMapTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<NodeConfigSourceConfigMap, NodeConfigSourceConfigMapDslBuilder> {
                requireScenario("kubeletConfigKey") {
                    given(NodeConfigSourceConfigMapDslBuilder())
                }

                requireScenario("name") {
                    given(NodeConfigSourceConfigMapDslBuilder()) {
                        kubeletConfigKey = PLACEHOLDER
                    }
                }

                requireScenario("namespace") {
                    given(NodeConfigSourceConfigMapDslBuilder()) {
                        kubeletConfigKey = PLACEHOLDER
                        name = PLACEHOLDER
                    }
                }
            }
    }
}