package io.violabs.picard.v2.resources.policy.network


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NetworkPolicySpecTest : FailureBuildSim<NetworkPolicySpec, NetworkPolicySpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NetworkPolicySpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<NetworkPolicySpec, NetworkPolicySpecDslBuilder> {
            requireScenario("podSelector") {
                given(NetworkPolicySpecDslBuilder())
            }
        }
    }
}