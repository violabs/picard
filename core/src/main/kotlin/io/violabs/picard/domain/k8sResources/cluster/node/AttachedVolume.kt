package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class AttachedVolume(
    val devicePath: String,
    val name: String
) {
    class Builder : DslBuilder<AttachedVolume> {
        var devicePath: String? = null
        var name: String? = null

        override fun build(): AttachedVolume {
            return AttachedVolume(
                devicePath = vRequireNotNull(this::devicePath),
                name = vRequireNotNull(this::name)
            )
        }
    }

    class Group : BuilderGroup<AttachedVolume, Builder>(Builder()) {
        fun volumes(): List<AttachedVolume>? = items()

        fun addAttachedVolume(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}