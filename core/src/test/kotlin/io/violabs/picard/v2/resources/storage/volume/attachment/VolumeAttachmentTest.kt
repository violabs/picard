package io.violabs.picard.v2.resources.storage.volume.attachment


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.storage.persistent.volume.PersistentVolumeSpec
import io.violabs.picard.v2.resources.storage.volume.source.VolumeAttachmentSource
import io.violabs.picard.v2.resources.storage.volume.source.VolumeAttachmentSourceDslBuilder
import org.junit.jupiter.api.BeforeAll

class VolumeAttachmentTest : SuccessBuildSim<VolumeAttachment, VolumeAttachmentV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            VolumeAttachmentTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val VOLUME_ERROR = VolumeError(
            errorCode = 1,
            message = PLACEHOLDER,
            time = NOW
        )

        private val VOLUME_ATTACHMENT_SOURCE = VolumeAttachmentSource(
            persistentVolumeName = PLACEHOLDER,
            inlineVolumeSpec = PersistentVolumeSpec()
        )

        private fun VolumeAttachmentSourceDslBuilder.sharedVolumeAttachmentSource() {
            persistentVolumeName = PLACEHOLDER
            inlineVolumeSpec {

            }
        }

        private val SUCCESS_POSSIBILITIES = possibilities<VolumeAttachment, VolumeAttachmentV2DslBuilder> {
            scenario {
                id = "minimum"
                given(VolumeAttachmentV2DslBuilder())
                expected = VolumeAttachment()
            }

            scenario {
                id = "full"
                given(VolumeAttachmentV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        attacher = PLACEHOLDER
                        nodeName = PLACEHOLDER
                        source {
                            sharedVolumeAttachmentSource()
                        }
                    }
                }
                expected = VolumeAttachment(
                    metadata = Common.OBJECT_META,
                    spec = VolumeAttachmentSpec(
                        attacher = PLACEHOLDER,
                        nodeName = PLACEHOLDER,
                        source = VOLUME_ATTACHMENT_SOURCE
                    )
                )
            }
        }
    }
}