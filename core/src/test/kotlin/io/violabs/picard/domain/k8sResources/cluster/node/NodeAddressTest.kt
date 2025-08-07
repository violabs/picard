package io.violabs.picard.domain.k8sResources.cluster.node


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NodeAddressTest : FailureBuildSim<NodeAddress, NodeAddressDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeAddressTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<NodeAddress, NodeAddressDslBuilder> {
            requireScenario("address") {
                given(NodeAddressDslBuilder())
            }

            requireScenario("type") {
                given(NodeAddressDslBuilder()) {
                    address = PLACEHOLDER
                }
            }
        }
    }
}