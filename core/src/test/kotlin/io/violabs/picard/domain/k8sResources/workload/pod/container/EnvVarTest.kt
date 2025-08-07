package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.verifyRequiredField
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class EnvVarTest : FailureBuildSim<EnvVar, EnvVarDslBuilder>() {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            buildSetup(
                EnvVarTest::class,
                failureScenariosSet = FAILURE_POSSIBILITIES
            )
        }
    }
}

private val FAILURE_POSSIBILITIES = possibilities<EnvVar, EnvVarDslBuilder> {
    scenario {
        id = "missing name"
        given(EnvVarDslBuilder())
        exceptionMessage = withTemplate("name")
    }
}