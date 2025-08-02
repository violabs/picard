package io.violabs.picard.domain.k8sResources.workload.controllerRevision

import io.violabs.picard.common.ResourceDslBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadResource

@Deprecated("Use v2", ReplaceWith("ControllerRevisionV2", "io.violabs.picard.v2.resources.workload.controller.revision.ControllerRevisionV2"))
data class ControllerRevision(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    val revision: Long,
    override val metadata: ObjectMetadata? = null,
    val data: Any? = null
) : WorkloadResource<ControllerRevision.Version, ObjectMetadata> {
    interface Version : APIVersion

    class Builder : ResourceDslBuilder<ControllerRevision>() {
        var revision: Long? = null
        private var data: Any? = null

        fun data(block: () -> Any?) {
            data = block()
        }

        override fun build(): ControllerRevision {
            return ControllerRevision(
                revision = vRequireNotNull(this::revision),
                metadata = metadata,
                data = data
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ControllerRevision, Builder>(Builder()) {
        fun controllerRevisionItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}