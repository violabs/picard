package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class WindowsPodSecurityContextOptionsTest :
    SuccessBuildSim<WindowsSecurityContextOptions, WindowsSecurityContextOptions.Builder>() {

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
    possibilities<WindowsSecurityContextOptions, WindowsSecurityContextOptions.Builder> {
        scenario {
            id = "false boolean values"
            given(WindowsSecurityContextOptions.Builder()) {
                hostProcess = false
            }
            expected = WindowsSecurityContextOptions(
                hostProcess = false
            )
        }
    }