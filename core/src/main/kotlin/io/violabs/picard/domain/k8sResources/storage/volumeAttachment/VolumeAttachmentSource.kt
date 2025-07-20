package io.violabs.picard.domain.k8sResources.storage.volumeAttachment

import io.violabs.picard.common.DslBuilder

data class VolumeAttachmentSource(
    val persistentVolumeName: String? = null,
    val inlineVolumeSpec: InlineVolumeSpec? = null
) {
    class Builder : DslBuilder<VolumeAttachmentSource> {
        var persistentVolumeName: String? = null
        private var inlineVolumeSpec: InlineVolumeSpec? = null

        fun inlineVolumeSpec(block: InlineVolumeSpec.Builder.() -> Unit) {
            inlineVolumeSpec = InlineVolumeSpec.Builder().apply(block).build()
        }

        override fun build(): VolumeAttachmentSource {
            return VolumeAttachmentSource(
                persistentVolumeName = persistentVolumeName,
                inlineVolumeSpec = inlineVolumeSpec
            )
        }
    }
}