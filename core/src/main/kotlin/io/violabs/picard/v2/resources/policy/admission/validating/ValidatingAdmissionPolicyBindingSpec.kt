package io.violabs.picard.v2.resources.policy.admission.validating

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ValidatingAdmissionPolicyBindingSpec is the specification of the ValidatingAdmissionPolicyBinding.
 */
@GeneratedDsl
data class ValidatingAdmissionPolicyBindingSpec(
    /**
     * MatchResources declares what resources match this binding and will be validated by it. Note that this is 
     * intersected with the policy's matchConstraints, so only requests that are matched by the policy can be 
     * selected by this. If this is unset, all resources matched by the policy are validated by this binding 
     * When resourceRules is unset, it does not constrain resource matching. If a resource is matched by the 
     * other fields of this object, it will be validated. Note that this is differs from ValidatingAdmissionPolicy 
     * matchConstraints, where resourceRules are required.
     */
    val matchResources: MatchResources? = null,
    /**
     * paramRef specifies the parameter resource used to configure the admission control policy. It should point 
     * to a resource of the type specified in ParamKind of the bound ValidatingAdmissionPolicy. If the policy 
     * specifies a ParamKind and the resource referred to by ParamRef does not exist, this binding is considered 
     * mis-configured and the FailurePolicy of the ValidatingAdmissionPolicy applied. If the policy does not 
     * specify a ParamKind then this field is ignored, and the rules are evaluated without a param.
     */
    val paramRef: ParamRef? = null,
    /**
     * PolicyName references a ValidatingAdmissionPolicy name which the ValidatingAdmissionPolicyBinding binds to. 
     * If the referenced resource does not exist, this binding is considered invalid and will be ignored Required.
     */
    val policyName: String? = null,
    /**
     * validationActions declares how Validations of the referenced ValidatingAdmissionPolicy are enforced. If a 
     * validation evaluates to false it is always enforced according to these actions.
     *
     * Failures defined by the ValidatingAdmissionPolicy's FailurePolicy are enforced according to these actions 
     * only if the FailurePolicy is set to Fail, otherwise the failures are ignored. This includes compilation 
     * errors, runtime errors and misconfigurations of the policy.
     *
     * validationActions is declared as a set of action values. Order does not matter. validationActions may not 
     * contain duplicates of the same action.
     *
     * The supported actions values are:
     * - "Deny" specifies that a validation failure results in a denied request.
     * - "Warn" specifies that a validation failure is reported to the request client in HTTP Warning headers, 
     *   with a warning code of 299. Warnings can be sent both for allowed or denied admission responses.
     * - "Audit" specifies that a validation failure is included in the published audit event for the request.
     *
     * Clients should expect to handle additional values by ignoring any values not recognized.
     *
     * "Deny" and "Warn" may not be used together since this combination needlessly duplicates the validation 
     * failure both in the API response body and the HTTP warning headers.
     *
     * Required.
     */
    val validationActions: List<String>? = null
)