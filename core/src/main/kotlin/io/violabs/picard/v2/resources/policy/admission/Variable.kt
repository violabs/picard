package io.violabs.picard.v2.resources.policy.admission

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Variable is the definition of a variable that is used for composition. A variable is
 * defined as a named expression.
 */
@GeneratedDsl(withListGroup = true)
data class Variable(
    /**
     * Expression is the expression that will be evaluated as the value of the variable.
     * The CEL expression has access to the same identifiers as the CEL expressions in Validation.
     */
    val expression: String,
    /**
     * Name is the name of the variable. The name must be a valid CEL identifier and unique
     * among all variables. The variable can be accessed in other expressions through variables
     * For example, if name is "foo", the variable will be available as variables.foo
     */
    val name: String
)