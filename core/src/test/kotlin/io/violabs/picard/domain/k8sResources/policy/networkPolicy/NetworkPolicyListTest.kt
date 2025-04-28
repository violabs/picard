package io.violabs.picard.domain.k8sResources.policy.networkPolicy


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NetworkPolicyListTest : FullBuildSim<NetworkPolicyList, NetworkPolicyList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NetworkPolicyListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<NetworkPolicyList, NetworkPolicyList.Builder> {
            scenario {
                id = "minimum"
                given(NetworkPolicyList.Builder()) {
                    items {
                        policy {  }
                    }
                }
                expected = NetworkPolicyList(
                    items = listOf(NetworkPolicy())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<NetworkPolicyList, NetworkPolicyList.Builder> {
            requireNotEmptyScenario("items") {
                given(NetworkPolicyList.Builder())
            }
        }
    }
}