package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SeccompProfileTest : FailureBuildSim<SeccompProfile, SeccompProfile.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            buildSetup(
                SeccompProfileTest::class,
                failureScenariosSet = FAILURE_POSSIBILITIES
            )
        }
    }
}

private val FAILURE_POSSIBILITIES = possibilities<SeccompProfile, SeccompProfile.Builder> {
    scenario {
        id = "missing type"
        given(SeccompProfile.Builder())
        exceptionMessage = withTemplate("type")
    }
}