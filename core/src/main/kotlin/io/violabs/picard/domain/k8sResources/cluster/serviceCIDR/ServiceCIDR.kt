package io.violabs.picard.domain.k8sResources.cluster.serviceCIDR

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ServiceCondition

data class ServiceCIDR(
    override val apiVersion: Version = KAPIVersion.NetworkingV1Beta1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<ServiceCIDR.Version> {
    interface Version : APIVersion

    data class Spec(val cidrs: List<String>? = null) : BaseSpec

    data class Status(
        val conditions: List<ServiceCondition>? = null
    ) : BaseStatus
}