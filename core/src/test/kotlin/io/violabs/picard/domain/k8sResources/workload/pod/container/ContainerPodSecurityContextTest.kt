package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ContainerPodSecurityContextTest : SuccessBuildSim<ContainerSecurityContext, ContainerSecurityContext.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            buildSetup(
                ContainerPodSecurityContextTest::class,
                SUCCESS_POSSIBILITIES
            )
        }
    }
}

private val SUCCESS_POSSIBILITIES = possibilities<ContainerSecurityContext, ContainerSecurityContext.Builder> {
    scenario {
        id = "false boolean values"
        given(ContainerSecurityContext.Builder()) {
            allowPrivilegeEscalation = false
            privileged = false
            readOnlyRootFilesystem = false
            runAsNonRoot = false
        }
        expected = ContainerSecurityContext(
            allowPrivilegeEscalation = false,
            privileged = false,
            readOnlyRootFilesystem = false,
            runAsNonRoot = false
        )
    }
}