package io.violabs.picard.domain.k8sResources.policy


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IPBlockTest : FailureBuildSim<IPBlock, IPBlockDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IPBlockTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<IPBlock, IPBlockDslBuilder> {
            requireScenario("cidr") {
                given(IPBlockDslBuilder())
            }
        }
    }
}