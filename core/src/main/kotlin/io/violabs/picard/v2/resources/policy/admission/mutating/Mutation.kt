package io.violabs.picard.v2.resources.policy.admission.mutating

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Mutation specifies the CEL expression which is used to apply the Mutation.
 */
@GeneratedDsl(withListGroup = true)
data class Mutation(
    /**
     * patchType indicates the patch strategy used. Allowed values are "ApplyConfiguration" and "JSONPatch". Required.
     */
    val patchType: String,
    /**
     * applyConfiguration defines the desired configuration values of an object. The configuration is applied to the
     * admission object using structured merge diff. A CEL expression is used to create apply configuration.
     */
    val applyConfiguration: ApplyConfiguration? = null,
    /**
     * jsonPatch defines a JSON patch operation to perform a mutation to the object. A CEL expression is used to
     * create the JSON patch.
     */
    val jsonPatch: JsonPatch? = null
)