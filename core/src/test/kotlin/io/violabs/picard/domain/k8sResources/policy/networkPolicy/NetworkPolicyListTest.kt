package io.violabs.picard.domain.k8sResources.policy.networkPolicy


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NetworkPolicyListTest : FullBuildSim<NetworkPolicyList, NetworkPolicyListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NetworkPolicyListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<NetworkPolicyList, NetworkPolicyListDslBuilder> {
            scenario {
                id = "minimum"
                given(NetworkPolicyListDslBuilder()) {
                    items {
                        networkPolicyItem {  }
                    }
                }
                expected = NetworkPolicyList(
                    items = listOf(NetworkPolicy())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<NetworkPolicyList, NetworkPolicyListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(NetworkPolicyListDslBuilder())
            }
        }
    }
}