package io.violabs.picard.domain.v2.resources.storage.persistent.volume.claim

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.configstorage.persistent.volume.claim.ModifyVolumeStatus
import io.violabs.picard.v2.resources.configstorage.persistent.volume.claim.ModifyVolumeStatusDslBuilder
import org.junit.jupiter.api.BeforeAll

class ModifyVolumeStatusTest : FailureBuildSim<ModifyVolumeStatus, ModifyVolumeStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ModifyVolumeStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ModifyVolumeStatus, ModifyVolumeStatusDslBuilder> {
            requireScenario("status") {
                given(ModifyVolumeStatusDslBuilder())
            }
        }
    }
}