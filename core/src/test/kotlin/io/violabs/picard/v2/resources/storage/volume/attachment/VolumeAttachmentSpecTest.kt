package io.violabs.picard.v2.resources.storage.volume.attachment


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class VolumeAttachmentSpecTest : FailureBuildSim<VolumeAttachmentSpec, VolumeAttachmentSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            VolumeAttachmentSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<VolumeAttachmentSpec, VolumeAttachmentSpecDslBuilder> {
            requireScenario("attacher") {
                given(VolumeAttachmentSpecDslBuilder())
            }
        }
    }
}