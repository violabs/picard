package io.violabs.picard.k8sResources.workload.pod.container

import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import io.violabs.picard.domain.ImagePullPolicy
import io.violabs.picard.domain.RestartPolicy
import io.violabs.picard.domain.k8sResources.workload.pod.container.EphemeralContainer
import io.violabs.picard.k8sResources.workload.pod.container.common.*
import io.violabs.picard.k8sResources.workload.pod.container.common.ContainerSim.Companion.fullScenario
import io.violabs.picard.possibilities
import io.violabs.picard.verifyHappyPath
import io.violabs.picard.verifyRequiredField
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate

class EphemeralContainerTest : UnitSim() {

    @Test
    fun `build minimum exception`() = verifyRequiredField(
        "name",
        EphemeralContainer.Builder(),
    )

    @TestTemplate
    fun `build happy path - #scenario`(builder: EphemeralContainer.Builder, container: EphemeralContainer) =
        verifyHappyPath(
            builder, container
        )

    companion object {
        private val SUCCESS_SIMULATION_GROUP = SimulationGroup.vars("scenario", "builder", "container")

        @JvmStatic
        @BeforeAll
        fun setup() {
            SUCCESS_POSSIBILITIES.scenarios.forEach {
                SUCCESS_SIMULATION_GROUP.with(it.id, it.given, it.expected)
            }

            ContainerSim.Companion

            setup<EphemeralContainerTest>(
                SUCCESS_SIMULATION_GROUP to { this::`build happy path - #scenario` }
            )
        }
    }
}

private const val CONTAINER_NAME = "test"

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
    restartPolicy = RestartPolicy.ALWAYS,
    securityContext = SECURITY_CONTEXT,
    stdin = true,
    stdinOnce = true,
    tty = true,
)

private val SUCCESS_POSSIBILITIES = possibilities<EphemeralContainer, EphemeralContainer.Builder> {
    val containerName = "test"

    scenario {
        id = "minimum"
        description = "least required fields"
        given(EphemeralContainer.Builder()) {
            name = containerName
        }
        expected = EphemeralContainer(containerName)
    }

    fullScenario(
        this,
        EphemeralContainer.Builder(),
        FULL_CONTAINER
    ) {
        targetContainerName = containerName
    }
}