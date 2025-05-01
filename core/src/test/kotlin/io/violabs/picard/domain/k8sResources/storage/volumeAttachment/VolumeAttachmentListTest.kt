package io.violabs.picard.domain.k8sResources.storage.volumeAttachment


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class VolumeAttachmentListTest : FullBuildSim<VolumeAttachmentList, VolumeAttachmentList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            VolumeAttachmentListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<VolumeAttachmentList, VolumeAttachmentList.Builder> {
            scenario {
                id = "minimum"
                given(VolumeAttachmentList.Builder()) {
                    items {
                        attachment {  }
                    }
                }
                expected = VolumeAttachmentList(
                    items = listOf(VolumeAttachment())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<VolumeAttachmentList, VolumeAttachmentList.Builder> {
            requireNotEmptyScenario("items") {
                given(VolumeAttachmentList.Builder())
            }
        }
    }
}