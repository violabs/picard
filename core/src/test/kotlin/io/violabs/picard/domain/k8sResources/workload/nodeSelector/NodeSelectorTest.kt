package io.violabs.picard.domain.k8sResources.workload.nodeSelector


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NodeSelectorTest : FailureBuildSim<NodeSelector, NodeSelector.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeSelectorTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<NodeSelector, NodeSelector.Builder> {
            requireNotEmptyScenario("nodeSelectorTerms") {
                given(NodeSelector.Builder())
            }
        }
    }
}