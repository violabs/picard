package io.violabs.picard.v2.resources.policy.admission.validating

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * TypeChecking contains results of type checking the expressions in the ValidatingAdmissionPolicy
 */
@GeneratedDsl
data class TypeChecking(
    /**
     * The type checking warnings for each expression.
     */
    val expressionWarnings: List<ExpressionWarning>? = null
)