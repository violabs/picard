package io.violabs.picard.domain.k8sResources.policy.limitRange

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.workload.BaseSpec

class LimitRange(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null
) : K8sResource<LimitRange.Version> {
    interface Version : APIVersion

    data class Spec(
        val limits: List<Item>
    ) : BaseSpec

    data class Item(
        val type: String,
        val default: Map<String, Quantity>? = null,
        val defaultRequest: Map<String, Quantity>? = null,
        val max: Map<String, Quantity>? = null,
        val maxLimitRequestRatio: Map<String, Quantity>? = null,
        val min: Map<String, Quantity>? = null
    )
}