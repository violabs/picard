package io.violabs.picard.k8sResources.workload.pod.container

import io.violabs.geordi.UnitSim
import io.violabs.picard.domain.k8sResources.workload.pod.container.ContainerPort
import io.violabs.picard.verifyHappyPath
import io.violabs.picard.verifyRequiredField
import kotlin.test.Test

class ContainerPortTest : UnitSim() {

    @Test
    fun `build minimum exception`() = verifyRequiredField("containerPort", ContainerPort.Builder())

    @Test
    fun `build minimum happy path`() = verifyHappyPath(
        ContainerPort.Builder(),
        ContainerPort(containerPort = 8080)
    ) {
        containerPort = 8080
    }
}