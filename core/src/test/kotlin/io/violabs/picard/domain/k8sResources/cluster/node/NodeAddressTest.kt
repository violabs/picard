package io.violabs.picard.domain.k8sResources.cluster.node


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NodeAddressTest : FailureBuildSim<NodeAddress, NodeAddress.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeAddressTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<NodeAddress, NodeAddress.Builder> {
            requireScenario("address") {
                given(NodeAddress.Builder())
            }

            requireScenario("type") {
                given(NodeAddress.Builder()) {
                    address = PLACEHOLDER
                }
            }
        }
    }
}