package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class AppArmorProfileTest : FailureBuildSim<AppArmorProfile, AppArmorProfileDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            buildSetup(
                AppArmorProfileTest::class,
                failureScenariosSet = FAILURE_POSSIBILITIES
            )
        }
    }
}

private val FAILURE_POSSIBILITIES = possibilities<AppArmorProfile, AppArmorProfileDslBuilder> {
    scenario {
        id = "missing type"
        given(AppArmorProfileDslBuilder())
        exceptionMessage = withTemplate("type")
    }
}