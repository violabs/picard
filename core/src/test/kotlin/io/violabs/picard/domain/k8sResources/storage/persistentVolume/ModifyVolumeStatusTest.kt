package io.violabs.picard.domain.k8sResources.storage.persistentVolume

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ModifyVolumeStatusTest : FailureBuildSim<ModifyVolumeStatus, ModifyVolumeStatus.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ModifyVolumeStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ModifyVolumeStatus, ModifyVolumeStatus.Builder> {
            requireScenario("status") {
                given(ModifyVolumeStatus.Builder())
            }
        }
    }
}