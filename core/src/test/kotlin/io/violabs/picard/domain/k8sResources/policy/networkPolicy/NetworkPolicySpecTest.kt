package io.violabs.picard.domain.k8sResources.policy.networkPolicy


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NetworkPolicySpecTest : FailureBuildSim<NetworkPolicy.Spec, NetworkPolicy.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NetworkPolicySpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<NetworkPolicy.Spec, NetworkPolicy.Spec.Builder> {
            requireScenario("podSelector") {
                given(NetworkPolicy.Spec.Builder())
            }
        }
    }
}