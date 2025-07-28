package io.violabs.picard.v2.resources.policy.admission.validating

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ParamKind is a tuple of Group Kind and Version.
 */
@GeneratedDsl
data class ParamKind(
    /**
     * APIVersion is the API group version the resources belong to. In format of "group/version". Required.
     */
    val apiVersion: String? = null,
    /**
     * Kind is the API kind the resources belong to. Required.
     */
    val kind: String? = null
)