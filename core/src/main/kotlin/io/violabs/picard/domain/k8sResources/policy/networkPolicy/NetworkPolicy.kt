package io.violabs.picard.domain.k8sResources.policy.networkPolicy

import io.violabs.picard.domain.LabelSelector
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec

class NetworkPolicy(
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : K8sResource<NetworkPolicy.Version> {
    interface Version : APIVersion

    data class Spec(
        val podSelector: LabelSelector,
        val policyTypes: List<String>? = null,
        val ingress: List<NetworkPolicyIngressRule>? = null,
    ) : BaseSpec
}