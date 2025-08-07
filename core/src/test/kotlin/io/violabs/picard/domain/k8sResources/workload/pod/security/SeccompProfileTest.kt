package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SeccompProfileTest : FailureBuildSim<SeccompProfile, SeccompProfileDslBuilder>() {
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

private val FAILURE_POSSIBILITIES = possibilities<SeccompProfile, SeccompProfileDslBuilder> {
    scenario {
        id = "missing type"
        given(SeccompProfileDslBuilder())
        exceptionMessage = withTemplate("type")
    }
}