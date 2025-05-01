package io.violabs.picard.domain.k8sResources.storage.persistentVolume

import io.violabs.picard.domain.BaseNodeAffinity
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.k8sResources.workload.nodeSelector.NodeSelector

data class VolumeNodeAffinity(
    val required: NodeSelector? = null,
) : BaseNodeAffinity {
    class Builder : DSLBuilder<VolumeNodeAffinity> {
        private var required: NodeSelector? = null

        fun required(block: NodeSelector.Builder.() -> Unit) {
            required = NodeSelector.Builder().apply(block).build()
        }

        override fun build(): VolumeNodeAffinity {
            return VolumeNodeAffinity(
                required = required
            )
        }
    }
}