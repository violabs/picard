package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class AppArmorProfileTest : FailureBuildSim<AppArmorProfile, AppArmorProfile.Builder>() {
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

private val FAILURE_POSSIBILITIES = possibilities<AppArmorProfile, AppArmorProfile.Builder> {
    scenario {
        id = "missing type"
        given(AppArmorProfile.Builder())
        exceptionMessage = withTemplate("type")
    }
}