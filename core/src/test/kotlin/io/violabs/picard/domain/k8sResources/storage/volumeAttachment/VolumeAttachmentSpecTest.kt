package io.violabs.picard.domain.k8sResources.storage.volumeAttachment


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class VolumeAttachmentSpecTest : FailureBuildSim<VolumeAttachment.Spec, VolumeAttachment.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            VolumeAttachmentSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<VolumeAttachment.Spec, VolumeAttachment.Spec.Builder> {
            requireScenario("attacher") {
                given(VolumeAttachment.Spec.Builder())
            }
        }
    }
}