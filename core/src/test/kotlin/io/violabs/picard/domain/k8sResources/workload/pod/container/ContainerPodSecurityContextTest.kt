package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ContainerPodSecurityContextTest : SuccessBuildSim<ContainerSecurityContext, ContainerSecurityContextDslBuilder>() {
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

private val SUCCESS_POSSIBILITIES = possibilities<ContainerSecurityContext, ContainerSecurityContextDslBuilder> {
    scenario {
        id = "false boolean values"
        given(ContainerSecurityContextDslBuilder()) {
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