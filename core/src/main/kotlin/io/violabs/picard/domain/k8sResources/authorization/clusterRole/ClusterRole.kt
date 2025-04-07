package io.violabs.picard.domain.k8sResources.authorization.clusterRole

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authorization.AggregationRule
import io.violabs.picard.domain.k8sResources.authorization.PolicyRule

class ClusterRole(
    override val apiVersion: Version = KAPIVersion.RBACAuthorizationV1,
    override val metadata: ObjectMetadata? = null,
    val aggregationRule: AggregationRule? = null,
    val rules: List<PolicyRule>? = null
) : K8sResource<ClusterRole.Version> {

    interface Version : APIVersion
}