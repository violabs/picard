package io.violabs.picard.domain.k8sResources.storage.volumeAttachment

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder

data class InlineVolumeSpec(
    val attached: Boolean,
    val attachError: VolumeError? = null,
    val attachmentMetadata: Map<String, String>? = null,
    val detachError: VolumeError? = null
) {
    class Builder : DSLBuilder<InlineVolumeSpec> {
        private var attached: Boolean? = null
        private var attachError: VolumeError? = null
        private var attachmentMetadata: Map<String, String>? = null
        private var detachError: VolumeError? = null

        fun attached(value: Boolean = true) {
            attached = value
        }

        fun attachError(block: VolumeError.Builder.() -> Unit) {
            attachError = VolumeError.Builder().apply(block).build()
        }

        fun attachmentMetadata(vararg value: Pair<String, String>) {
            attachmentMetadata = value.toMap()
        }

        fun detachError(block: VolumeError.Builder.() -> Unit) {
            detachError = VolumeError.Builder().apply(block).build()
        }

        override fun build(): InlineVolumeSpec {
            return InlineVolumeSpec(
                attached = vRequireNotNull(this::attached),
                attachError = attachError,
                attachmentMetadata = attachmentMetadata,
                detachError = detachError
            )
        }
    }
}