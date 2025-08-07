package io.violabs.picard.domain.k8sResources.storage.volumeAttachment


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class VolumeAttachmentListTest : FullBuildSim<VolumeAttachmentList, VolumeAttachmentListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            VolumeAttachmentListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<VolumeAttachmentList, VolumeAttachmentListDslBuilder> {
            scenario {
                id = "minimum"
                given(VolumeAttachmentListDslBuilder()) {
                    items {
                        volumeAttachmentItem {  }
                    }
                }
                expected = VolumeAttachmentList(
                    items = listOf(VolumeAttachment())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<VolumeAttachmentList, VolumeAttachmentListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(VolumeAttachmentListDslBuilder())
            }
        }
    }
}