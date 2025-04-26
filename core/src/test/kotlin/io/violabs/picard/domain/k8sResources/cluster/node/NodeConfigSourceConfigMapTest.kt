package io.violabs.picard.domain.k8sResources.cluster.node


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NodeConfigSourceConfigMapTest : FailureBuildSim<NodeConfigSourceConfigMap, NodeConfigSourceConfigMap.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeConfigSourceConfigMapTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<NodeConfigSourceConfigMap, NodeConfigSourceConfigMap.Builder> {
                requireScenario("kubeletConfigKey") {
                    given(NodeConfigSourceConfigMap.Builder())
                }

                requireScenario("name") {
                    given(NodeConfigSourceConfigMap.Builder()) {
                        kubeletConfigKey = PLACEHOLDER
                    }
                }

                requireScenario("namespace") {
                    given(NodeConfigSourceConfigMap.Builder()) {
                        kubeletConfigKey = PLACEHOLDER
                        name = PLACEHOLDER
                    }
                }
            }
    }
}