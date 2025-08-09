package io.violabs.picard.v2.resources.policy.network


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NetworkPolicyPortTest : SuccessBuildSim<NetworkPolicyPort, NetworkPolicyPortDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NetworkPolicyPortTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<NetworkPolicyPort, NetworkPolicyPortDslBuilder> {
            scenario {
                id = "minimum"
                given(NetworkPolicyPortDslBuilder()) {
                    port = IntOrString(8080)
                }
                expected = NetworkPolicyPort(
                    port = IntOrString(str = "8080")
                )
            }
        }
    }
}