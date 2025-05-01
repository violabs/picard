package io.violabs.picard.domain.k8sResources.workload.daemonSet


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DaemonSetSpecTest : FailureBuildSim<DaemonSet.Spec, DaemonSet.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DaemonSetSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<DaemonSet.Spec, DaemonSet.Spec.Builder> {
            requireScenario("selector") {
                given(DaemonSet.Spec.Builder())
            }
        }
    }
}