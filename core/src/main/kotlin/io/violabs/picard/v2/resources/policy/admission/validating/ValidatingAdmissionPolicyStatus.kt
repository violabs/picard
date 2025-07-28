package io.violabs.picard.v2.resources.policy.admission.validating

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ValidatingAdmissionPolicyStatus represents the status of an admission validation policy.
 */
@GeneratedDsl
data class ValidatingAdmissionPolicyStatus(
    /**
     * The conditions represent the latest available observations of a policy's current state.
     */
    val conditions: List<ValidatingAdmissionPolicyCondition>? = null,
    /**
     * The generation observed by the controller.
     */
    val observedGeneration: Long? = null,
    /**
     * The results of type checking for each expression. Presence of this field indicates 
     * the completion of the type checking.
     */
    val typeChecking: TypeChecking? = null
)