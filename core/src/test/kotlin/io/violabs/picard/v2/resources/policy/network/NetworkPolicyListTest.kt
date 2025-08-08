package io.violabs.picard.v2.resources.policy.network

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
                        networkPolicy {  }
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