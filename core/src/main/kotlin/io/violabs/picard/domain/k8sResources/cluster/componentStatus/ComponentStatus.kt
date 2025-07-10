package io.violabs.picard.domain.k8sResources.cluster.componentStatus

import io.violabs.picard.common.ResourceDslBuilder
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.condition.ComponentCondition
import io.violabs.picard.domain.condition.ComponentConditionGroup
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterResource

data class ComponentStatus(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val conditions: List<ComponentCondition>? = null
) : ClusterResource<ComponentStatus.Version> {

    interface Version : APIVersion

    class Builder : ResourceDslBuilder<ComponentStatus>() {
        private var conditions: List<ComponentCondition>? = null

        fun conditions(scope: ComponentConditionGroup.() -> Unit) {
            conditions = ComponentCondition.group(scope)
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