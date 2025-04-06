package io.violabs.picard.domain.k8sResources.service.ingressClass

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.domain.k8sResources.workload.BaseLoadBalancerIngress
import io.violabs.picard.domain.k8sResources.workload.BasePort
import io.violabs.picard.domain.k8sResources.workload.BaseSpec
import io.violabs.picard.domain.k8sResources.workload.BaseStatus


data class IngressClass(
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
) : K8sResource<IngressClass.Version> {
    interface Version : APIVersion

    data class Spec(
        val controller: String? = null,
        val parameters: ParametersReference? = null
    ) : BaseSpec

    data class ParametersReference(
        val kind: String,
        val name: String,
        val apiGroup: String? = null,
        val namespace: String? = null,
        val scope: String? = null
    )
}