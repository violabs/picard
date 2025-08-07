package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class WindowsPodSecurityContextOptionsTest :
    SuccessBuildSim<WindowsSecurityContextOptions, WindowsSecurityContextOptionsDslBuilder>() {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            buildSetup(
                WindowsPodSecurityContextOptionsTest::class,
                SUCCESS_POSSIBILITIES
            )
        }
    }
}

private val SUCCESS_POSSIBILITIES =
    possibilities<WindowsSecurityContextOptions, WindowsSecurityContextOptionsDslBuilder> {
        scenario {
            id = "false boolean values"
            given(WindowsSecurityContextOptionsDslBuilder()) {
                hostProcess = false
            }
            expected = WindowsSecurityContextOptions(
                hostProcess = false
            )
        }
    }