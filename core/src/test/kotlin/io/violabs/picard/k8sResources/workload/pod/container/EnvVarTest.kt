package io.violabs.picard.k8sResources.workload.pod.container

import io.violabs.geordi.UnitSim
import io.violabs.picard.domain.k8sResources.workload.pod.container.EnvVar
import io.violabs.picard.verifyRequiredField
import org.junit.jupiter.api.Test

class EnvVarTest : UnitSim() {

    @Test
    fun `build minimum exception`() = verifyRequiredField("name", EnvVar.Builder())
}