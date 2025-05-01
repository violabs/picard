package io.violabs.picard.domain.k8sResources.storage.volumeAttachment


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class VolumeAttachmentTest : SuccessBuildSim<VolumeAttachment, VolumeAttachment.Builder>() {
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
            inlineVolumeSpec = InlineVolumeSpec(
                attached = true,
                attachError = VOLUME_ERROR,
                attachmentMetadata = STRING_MAP,
                detachError = VOLUME_ERROR
            )
        )

        private fun VolumeAttachmentSource.Builder.sharedVolumeAttachmentSource() {
            persistentVolumeName = PLACEHOLDER
            inlineVolumeSpec {
                attached()
                attachError {
                    errorCode = 1
                    message = PLACEHOLDER
                    time = NOW
                }
                attachmentMetadata(PLACEHOLDER to PLACEHOLDER)
                detachError {
                    errorCode = 1
                    message = PLACEHOLDER
                    time = NOW
                }
            }
        }

        private val SUCCESS_POSSIBILITIES = possibilities<VolumeAttachment, VolumeAttachment.Builder> {
            scenario {
                id = "minimum"
                given(VolumeAttachment.Builder())
                expected = VolumeAttachment()
            }

            scenario {
                id = "full"
                given(VolumeAttachment.Builder()) {
                    sharedMetadata()
                    spec {
                        attacher = PLACEHOLDER
                        nodeName = PLACEHOLDER
                        source {
                            sharedVolumeAttachmentSource()
                        }
                        volumeAttachmentSource {
                            sharedVolumeAttachmentSource()
                        }
                    }
                }
                expected = VolumeAttachment(
                    metadata = METADATA,
                    spec = VolumeAttachment.Spec(
                        attacher = PLACEHOLDER,
                        nodeName = PLACEHOLDER,
                        source = VOLUME_ATTACHMENT_SOURCE,
                        volumeAttachmentSource = VOLUME_ATTACHMENT_SOURCE
                    )
                )
            }
        }
    }
}