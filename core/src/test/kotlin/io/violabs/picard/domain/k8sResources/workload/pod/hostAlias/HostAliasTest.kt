package io.violabs.picard.domain.k8sResources.workload.pod.hostAlias

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class HostAliasTest : FailureBuildSim<HostAlias, HostAliasDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            HostAliasTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<HostAlias, HostAliasDslBuilder> {
            requireScenario("ip") {
                given(HostAliasDslBuilder())
            }
        }
    }
}