package io.violabs.picard.v2.resources.storage.volume.attachment

/**
 * VolumeAttachmentStatus is the status of a VolumeAttachment request.
 */
data class VolumeAttachmentStatus(
    /**
     * attached indicates the volume is successfully attached. This field must
     * only be set by the entity completing the attach operation, i.e. the external-attacher.
     */
    val attached: Boolean,
    /**
     * attachError represents the last error encountered during attach operation,
     * if any. This field must only be set by the entity completing the attach
     * operation, i.e. the external-attacher.
     *
     * VolumeError captures an error encountered during a volume operation.
     */
    val attachError: VolumeError? = null,
    /**
     * attachmentMetadata is populated with any information returned by the attach
     * operation, upon successful attach, that must be passed into subsequent WaitForAttach
     * or Mount calls. This field must only be set by the entity completing the attach
     * operation, i.e. the external-attacher.
     */
    val attachmentMetadata: Map<String, String>? = null,
    /**
     * detachError represents the last error encountered during detach operation, if any.
     * This field must only be set by the entity completing the detach operation, i.e. the external-attacher.
     *
     * VolumeError captures an error encountered during a volume operation.
     */
    val detachError: VolumeError? = null
)