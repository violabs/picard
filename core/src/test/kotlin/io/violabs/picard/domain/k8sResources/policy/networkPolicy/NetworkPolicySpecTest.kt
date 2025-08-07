package io.violabs.picard.domain.k8sResources.policy.networkPolicy


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