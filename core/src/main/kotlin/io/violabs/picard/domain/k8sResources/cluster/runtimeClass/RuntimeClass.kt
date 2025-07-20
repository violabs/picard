package io.violabs.picard.domain.k8sResources.cluster.runtimeClass

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.common.ResourceDslBuilder
import io.violabs.picard.domain.k8sResources.*
import io.violabs.picard.domain.manifest.ClusterResource

data class RuntimeClass(
    override val apiVersion: Version = KAPIVersion.NodeV1,
    val handler: String,
    override val metadata: ObjectMetadata? = null,
    val overhead: RuntimeClassOverhead? = null,
    val scheduling: RuntimeClassScheduling? = null
    ) : ClusterResource<RuntimeClass.Version, ObjectMetadata> {
    interface Version : APIVersion

    class Builder : ResourceDslBuilder<RuntimeClass>() {
        var handler: String? = null
        private var overhead: RuntimeClassOverhead? = null
        private var scheduling: RuntimeClassScheduling? = null

        fun overhead(scope: RuntimeClassOverhead.Builder.() -> Unit) {
            overhead = RuntimeClassOverhead.Builder().apply(scope).build()
        }

        fun scheduling(scope: RuntimeClassScheduling.Builder.() -> Unit) {
            scheduling = RuntimeClassScheduling.Builder().apply(scope).build()
        }

        override fun build(): RuntimeClass {
            return RuntimeClass(
                metadata = metadata,
                handler = vRequireNotNull(this::handler),
                overhead = overhead,
                scheduling = scheduling
            )
        }
    }

    class Group : K8sListResource.ItemGroup<RuntimeClass, Builder>(Builder()) {
        fun runtime(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}