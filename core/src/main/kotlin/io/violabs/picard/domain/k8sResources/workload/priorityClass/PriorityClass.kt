package io.violabs.picard.domain.k8sResources.workload.priorityClass

import io.violabs.picard.common.ResourceDslBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadResource

@Deprecated("Use v2", ReplaceWith("io.violabs.picard.v2.resources.workload.scheduling.PriorityClassV2"))
data class PriorityClass(
    override val apiVersion: Version = KAPIVersion.SchedulingV1,
    override val metadata: ObjectMetadata? = null,
    val value: Int,
    val description: String? = null,
    val globalDefault: Boolean? = null,
    val preemptionPolicy: String? = null
) : WorkloadResource<PriorityClass.Version, ObjectMetadata> {
    interface Version : APIVersion

    class Builder : ResourceDslBuilder<PriorityClass>() {
        var value: Int? = null
        var description: String? = null
        private var globalDefault: Boolean? = null
        var preemptionPolicy: String? = null

        fun globalDefault(value: Boolean = true) {
            globalDefault = value
        }

        override fun build(): PriorityClass {
            return PriorityClass(
                metadata = metadata,
                value = vRequireNotNull(this::value),
                description = description,
                globalDefault = globalDefault,
                preemptionPolicy = preemptionPolicy
            )
        }
    }

    class Group : K8sListResource.ItemGroup<PriorityClass, Builder>(Builder()) {
        fun priorityClassItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}