package io.violabs.picard.domain.k8sResources.workload.pod.security


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SysctlTest : FailureBuildSim<Sysctl, SysctlDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SysctlTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<Sysctl, SysctlDslBuilder> {
            requireScenario("name") {
                given(SysctlDslBuilder())
            }

            requireScenario("value") {
                given(SysctlDslBuilder()) {
                    name = "sysctl_test"
                }
            }
        }
    }
}