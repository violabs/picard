package io.violabs.picard.domain.k8sResources.storage.volume

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder

data class Volume(
    val name: String,
    // exposed persistent volumes
    val persistentVolumeClaim: PersistentVolumeClaimVolumeSource? = null,
    // projections
    val emptyDir: Any? = null
) {
    class Builder : DslBuilder<Volume> {
        var name: String? = null
        private var persistentVolumeClaim: PersistentVolumeClaimVolumeSource? = null
        var emptyDir: Any? = null

        fun persistentVolumeClaim(block: PersistentVolumeClaimVolumeSource.Builder.() -> Unit) {
            persistentVolumeClaim = PersistentVolumeClaimVolumeSource.Builder().apply(block).build()
        }

        fun emptyDirObject() {
            emptyDir = mapOf<Any, Any>()
        }

        override fun build(): Volume  {
            return Volume(
                name = vRequireNotNull(this::name),
                persistentVolumeClaim = persistentVolumeClaim,
                emptyDir = emptyDir
            )
        }
    }

    class Group : BuilderGroup<Volume, Builder>(Builder()) {
        fun volumes(): List<Volume>? = items()

        fun addVolume(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}