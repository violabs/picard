package io.violabs.picard.domain.k8sResources.workload.pod.container


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LinuxContainerUserTest : FailureBuildSim<LinuxContainerUser, LinuxContainerUserDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LinuxContainerUserTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<LinuxContainerUser, LinuxContainerUserDslBuilder> {
            requireScenario("gid") {
                given(LinuxContainerUserDslBuilder())
            }

            requireScenario("uid") {
                given(LinuxContainerUserDslBuilder()) {
                    gid = 1
                }
            }
        }
    }
}