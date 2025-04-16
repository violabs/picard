package io.violabs.picard.domain.k8sResources.authorization.clusterRole

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.ResourceDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authorization.AggregationRule
import io.violabs.picard.domain.k8sResources.authorization.K8sRole
import io.violabs.picard.domain.k8sResources.authorization.PolicyRule

data class ClusterRole(
    override val apiVersion: Version = KAPIVersion.RBACAuthorizationV1,
    override val metadata: ObjectMetadata? = null,
    val aggregationRule: AggregationRule? = null,
    override val rules: List<PolicyRule>? = null
) : K8sResource<ClusterRole.Version>, K8sRole {
    interface Version : APIVersion

    class Builder : ResourceDSLBuilder<ClusterRole>() {
        private var aggregationRule: AggregationRule? = null
        private var rules: List<PolicyRule>? = null

        fun aggregationRule(scope: AggregationRule.Builder.() -> Unit) {
            aggregationRule = AggregationRule.Builder().apply(scope).build()
        }

        fun rules(scope: PolicyRule.Group.() -> Unit) {
            rules = PolicyRule.Group().apply(scope).rules()
        }

        override fun build(): ClusterRole {
            return ClusterRole(
                aggregationRule = aggregationRule,
                rules = rules,
                metadata = metadata
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ClusterRole, Builder>(Builder()) {
        fun role(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}