package io.violabs.picard.domain.k8sResources.workload.pod.container


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LinuxContainerUserTest : FailureBuildSim<LinuxContainerUser, LinuxContainerUser.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LinuxContainerUserTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<LinuxContainerUser, LinuxContainerUser.Builder> {
            requireScenario("gid") {
                given(LinuxContainerUser.Builder())
            }

            requireScenario("uid") {
                given(LinuxContainerUser.Builder()) {
                    gid = 1
                }
            }
        }
    }
}