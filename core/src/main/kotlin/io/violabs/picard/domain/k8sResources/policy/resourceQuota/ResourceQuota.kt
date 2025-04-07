package io.violabs.picard.domain.k8sResources.policy.resourceQuota

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.policy.ScopeSelector
import io.violabs.picard.domain.k8sResources.workload.BaseSpec

class ResourceQuota(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<ResourceQuota.Version> {
    interface Version : APIVersion

    data class Spec(
        val hard: Map<String, Quantity>? = null,
        val scopeSelector: ScopeSelector? = null,
        val scopes: List<String>? = null
    ) : BaseSpec
    
    data class Status(
        val hard: Map<String, Quantity>? = null,
        val used: Map<String, Quantity>? = null
    ) : BaseSpec
}