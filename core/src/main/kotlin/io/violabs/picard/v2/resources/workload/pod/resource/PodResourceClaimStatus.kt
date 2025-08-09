package io.violabs.picard.v2.resources.workload.pod.resource

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * PodResourceClaimStatus is stored in the PodStatus for each
 * PodResourceClaim which references a ResourceClaimTemplate.
 * It stores the generated name for the corresponding ResourceClaim.
 */
@GeneratedDsl(withListGroup = true)
data class PodResourceClaimStatus(
    /**
     * Name uniquely identifies this resource claim inside the pod.
     * This must match the name of an entry in pod.spec.resourceClaims,
     * which implies that the string must be a DNS_LABEL.
     */
    val name: String,
    /**
     * ResourceClaimName is the name of the ResourceClaim that was
     * generated for the Pod in the namespace of the Pod.
     * If this is unset, then generating a ResourceClaim was not necessary.
     * The pod.spec.resourceClaims entry can be ignored in this case.
     */
    val resourceClaimName: String? = null
)