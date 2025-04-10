package io.violabs.picard.k8sResources.workload.pod.container

import io.violabs.geordi.UnitSim
import io.violabs.picard.domain.k8sResources.workload.pod.container.ContainerSecurityContext
import io.violabs.picard.dslBuild
import org.junit.jupiter.api.Test

class ContainerSecurityContextTest : UnitSim() {

    @Test
    fun `build boolean false values`() = test {
        given {
            expect {
                ContainerSecurityContext(
                    allowPrivilegeEscalation = false,
                    privileged = false,
                    readOnlyRootFilesystem = false,
                    runAsNonRoot = false
                )
            }

            whenever {
                dslBuild(ContainerSecurityContext.Builder()) {
                    allowPrivilegeEscalation = false
                    privileged = false
                    readOnlyRootFilesystem = false
                    runAsNonRoot = false
                }
            }
        }
    }
}