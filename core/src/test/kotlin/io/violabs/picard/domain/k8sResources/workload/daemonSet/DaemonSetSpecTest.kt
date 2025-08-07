package io.violabs.picard.domain.k8sResources.workload.daemonSet


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DaemonSetSpecTest : FailureBuildSim<DaemonSetSpec, DaemonSetSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DaemonSetSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<DaemonSetSpec, DaemonSetSpecDslBuilder> {
            requireScenario("selector") {
                given(DaemonSetSpecDslBuilder())
            }
        }
    }
}