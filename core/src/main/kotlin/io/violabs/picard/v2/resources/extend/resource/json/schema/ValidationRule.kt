package io.violabs.picard.v2.resources.extend.resource.json.schema

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ValidationRule describes a validation rule written in the CEL expression language.
 */
@GeneratedDsl(withListGroup = true)
data class ValidationRule(
    /**
     * Rule represents the expression which will be evaluated by CEL.
     * ref: https://github.com/google/cel-spec
     * The Rule is scoped to the location of the x-kubernetes-validations extension in the schema.
     * The self variable in the CEL expression is bound to the scoped value.
     * Example: - Rule scoped to the root of a resource with a status subresource: {"rule": "self.status.actual <= self.spec.maxDesired"}
     */
    val rule: String? = null,
    /**
     * fieldPath represents the field path returned when the validation fails.
     * It must be a relative JSON path (i.e. with array notation) scoped to the location of this
     * x-kubernetes-validations extension in the schema and refer to an existing field.
     */
    val fieldPath: String? = null,
    /**
     * Message represents the message displayed when validation fails. The message is required if the Rule contains line breaks.
     * The message must not contain line breaks. If unset, the message is "failed rule: {Rule}".
     * e.g. "must be a URL with the host matching spec.host"
     */
    val message: String? = null,
    /**
     * MessageExpression declares a CEL expression that evaluates to the validation failure message that is returned when this rule fails.
     * Since messageExpression is used as a failure message, it must evaluate to a string.
     */
    val messageExpression: String? = null,
    /**
     * optionalOldSelf is used to opt a transition rule into evaluation even when the object is first created,
     * or if the old object is missing the value.
     */
    val optionalOldSelf: Boolean? = null,
    /**
     * reason provides a machine-readable validation failure reason that is returned to the caller when a request fails this validation rule.
     * The HTTP status code returned to the caller will match the reason of the reason of the first failed validation rule.
     * The currently supported reasons are: "FieldValueInvalid", "FieldValueForbidden", "FieldValueRequired", "FieldValueDuplicate".
     * If not set, default to use "FieldValueInvalid".
     */
    val reason: String? = null
)