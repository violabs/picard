package io.violabs.picard.domain.k8sResources.storage.volumeAttachment

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.ResourceSpecDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class VolumeAttachment(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : K8sResource<VolumeAttachment.Version> {
    interface Version : APIVersion

    data class Spec(
        val attacher: String,
        val nodeName: String? = null,
        val source: VolumeAttachmentSource? = null,
        val volumeAttachmentSource: VolumeAttachmentSource? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            var attacher: String? = null
            var nodeName: String? = null
            private var source: VolumeAttachmentSource? = null
            private var volumeAttachmentSource: VolumeAttachmentSource? = null

            fun source(block: VolumeAttachmentSource.Builder.() -> Unit) {
                source = VolumeAttachmentSource.Builder().apply(block).build()
            }

            fun volumeAttachmentSource(block: VolumeAttachmentSource.Builder.() -> Unit) {
                volumeAttachmentSource = VolumeAttachmentSource.Builder().apply(block).build()
            }

            override fun build(): Spec {
                return Spec(
                    attacher = vRequireNotNull(this::attacher),
                    nodeName = nodeName,
                    source = source,
                    volumeAttachmentSource = volumeAttachmentSource
                )
            }
        }
    }

    class Builder : ResourceSpecDSLBuilder<VolumeAttachment, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): VolumeAttachment {
            return VolumeAttachment(
                metadata = metadata,
                spec = spec
            )
        }
    }

    class Group : K8sListResource.ItemGroup<VolumeAttachment, Builder>(Builder()) {
        fun attachment(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}