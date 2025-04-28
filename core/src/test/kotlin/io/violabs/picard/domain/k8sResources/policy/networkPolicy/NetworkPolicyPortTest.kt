package io.violabs.picard.domain.k8sResources.policy.networkPolicy


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NetworkPolicyPortTest : SuccessBuildSim<NetworkPolicyPort, NetworkPolicyPort.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NetworkPolicyPortTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<NetworkPolicyPort, NetworkPolicyPort.Builder> {
            scenario {
                id = "minimum"
                given(NetworkPolicyPort.Builder()) {
                    port("8080")
                }
                expected = NetworkPolicyPort(
                    port = IntOrString(str = "8080")
                )
            }
        }
    }
}