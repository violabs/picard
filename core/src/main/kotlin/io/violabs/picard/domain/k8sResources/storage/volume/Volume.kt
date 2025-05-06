package io.violabs.picard.domain.k8sResources.storage.volume

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder

data class Volume(
    val name: String,
    val emptyDir: Any? = null
) {
    class Builder : DSLBuilder<Volume> {
        var name: String? = null
        var emptyDir: Any? = null

        fun emptyDirObject() {
            emptyDir = mapOf<Any, Any>()
        }

        override fun build(): Volume  {
            return Volume(
                name = vRequireNotNull(this::name),
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