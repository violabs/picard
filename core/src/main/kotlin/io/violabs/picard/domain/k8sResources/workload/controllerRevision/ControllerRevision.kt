package io.violabs.picard.domain.k8sResources.workload.controllerRevision

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class ControllerRevision(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    val revision: Long,
    override val metadata: ObjectMetadata? = null,
    val data: Any? = null
) : K8sResource<ControllerRevision.Version> {
    interface Version : APIVersion

    class Builder : ResourceDSLBuilder<ControllerRevision>() {
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
        fun controllerRevision(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}