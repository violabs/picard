package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.ImagePullPolicy
import io.violabs.picard.domain.RestartPolicy
import io.violabs.picard.domain.k8sResources.workload.pod.container.common.ContainerSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EphemeralContainerTest :
    ContainerSim<EphemeralContainer, EphemeralContainerDslBuilder>(EphemeralContainerDslBuilder()) {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            containerSetup(EphemeralContainerTest::class, SUCCESS_POSSIBILITIES)
        }

        private val FULL_CONTAINER = EphemeralContainer(
            name = CONTAINER_NAME,
            targetContainerName = CONTAINER_NAME,
            image = "test/image:latest",
            imagePullPolicy = ImagePullPolicy.Always,
            command = listOf("echo", "hello"),
            args = listOf("hello", "universe"),
            workingDir = "/home/test",
            env = listOf(ENV_VAR),
            envFrom = listOf(ENV_FROM_SOURCE),
            volumeMounts = listOf(VOLUME_MOUNT),
            volumeDevices = listOf(VOLUME_DEVICE),
            terminationMessagePath = "/dev/termination-log",
            terminationMessagePolicy = "FallbackToLogsOnError",
            restartPolicy = RestartPolicy.Always,
            securityContext = SECURITY_CONTEXT,
            stdin = true,
            stdinOnce = true,
            tty = true,
        )

        private val SUCCESS_POSSIBILITIES = possibilities<EphemeralContainer, EphemeralContainerDslBuilder> {
            scenario {
                id = "minimum"
                description = "least required fields"
                given(EphemeralContainerDslBuilder()) {
                    name = CONTAINER_NAME
                }
                expected = EphemeralContainer(CONTAINER_NAME)
            }

            fullScenario(
                this,
                EphemeralContainerDslBuilder(),
                FULL_CONTAINER
            ) {
                targetContainerName = CONTAINER_NAME
            }
        }
    }
}