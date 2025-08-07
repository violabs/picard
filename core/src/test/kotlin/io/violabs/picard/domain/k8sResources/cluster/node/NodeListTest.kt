package io.violabs.picard.domain.k8sResources.cluster.node


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NodeListTest : FullBuildSim<NodeList, NodeListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<NodeList, NodeListDslBuilder> {
            scenario {
                id = "minimum"
                given(NodeListDslBuilder()) {
                    items {
                        nodeItem {  }
                    }
                }
                expected = NodeList(
                    items = listOf(Node())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<NodeList, NodeListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(NodeListDslBuilder())
            }
        }
    }
}