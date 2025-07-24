package io.violabs.picard.v2.resources.storage.volume.attachment

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.storage.volume.source.VolumeAttachmentSource

/**
 * VolumeAttachmentSpec is the specification of a VolumeAttachment request.
 */
@GeneratedDsl
data class VolumeAttachmentSpec(
    /**
     * attacher (string), required
     *
     * attacher indicates the name of the volume driver that MUST handle this request. This is the name returned by GetPluginName().
     */
    val attacher: String,
    /**
     * nodeName (string), required
     *
     * nodeName represents the node that the volume should be attached to.
     */
    val nodeName: String,
    /**
     * source (VolumeAttachmentSource), required
     *
     * source represents the volume that should be attached.
     */
    val source: VolumeAttachmentSource
)