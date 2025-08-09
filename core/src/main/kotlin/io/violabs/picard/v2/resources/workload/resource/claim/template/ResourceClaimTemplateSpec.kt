package io.violabs.picard.v2.resources.workload.resource.claim.template

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.ObjectMeta
import io.violabs.picard.v2.resources.workload.resource.claim.ResourceClaimSpec

/**
 * ResourceClaimTemplateSpec contains the metadata and fields for a ResourceClaim.
 */
@GeneratedDsl
data class ResourceClaimTemplateSpec(
    /**
     * Spec for the ResourceClaim. The entire content is copied unchanged into the ResourceClaim that gets created from this template. The same fields as in a ResourceClaim are also valid here.
     */
    val spec: ResourceClaimSpec,
    /**
     * ObjectMeta may contain labels and annotations that will be copied into the ResourceClaim when creating it. No other fields are allowed and will be rejected during validation.
     */
    val metadata: ObjectMeta? = null
)