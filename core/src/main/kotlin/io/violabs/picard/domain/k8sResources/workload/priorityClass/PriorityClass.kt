package io.violabs.picard.domain.k8sResources.workload.priorityClass

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.ResourceDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class PriorityClass(
    override val apiVersion: Version = KAPIVersion.SchedulingV1,
    override val metadata: ObjectMetadata? = null,
    val value: Int,
    val description: String? = null,
    val globalDefault: Boolean? = null,
    val preemptionPolicy: String? = null
) : K8sResource<PriorityClass.Version> {
    interface Version : APIVersion

    class Builder : ResourceDSLBuilder<PriorityClass>() {
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
        fun priorityClass(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}