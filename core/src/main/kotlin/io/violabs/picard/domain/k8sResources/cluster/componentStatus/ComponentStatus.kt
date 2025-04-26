package io.violabs.picard.domain.k8sResources.cluster.componentStatus

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class ComponentStatus(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val conditions: List<ComponentCondition>? = null
) : K8sResource<ComponentStatus.Version> {

    interface Version : APIVersion

    class Builder : ResourceDSLBuilder<ComponentStatus>() {
        private var conditions: List<ComponentCondition>? = null

        fun conditions(scope: ConditionGroup<ComponentCondition, ComponentCondition.Builder>.() -> Unit) {
            conditions = ConditionGroup(ComponentCondition.Builder()).apply(scope).conditions()
        }

        override fun build(): ComponentStatus {
            return ComponentStatus(
                metadata = metadata,
                conditions = conditions
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ComponentStatus, Builder>(Builder()) {
        fun status(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}