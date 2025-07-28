package io.violabs.picard.v2.resources.policy.admission.mutating

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.policy.admission.MatchCondition
import io.violabs.picard.v2.resources.policy.admission.MatchResources
import io.violabs.picard.v2.resources.policy.admission.validating.ParamKind
import io.violabs.picard.v2.resources.policy.admission.Variable

/**
 * MutatingAdmissionPolicySpec is the specification of the desired behavior of the admission policy.
 */
@GeneratedDsl
data class MutatingAdmissionPolicySpec(
    /**
     * failurePolicy defines how to handle failures for the admission policy. Failures can occur from CEL
     * expression parse errors, type check errors, runtime errors and invalid or mis-configured policy
     * definitions or bindings.
     *
     * A policy is invalid if paramKind refers to a non-existent Kind. A binding is invalid if paramRef.name
     * refers to a non-existent resource.
     *
     * failurePolicy does not define how validations that evaluate to false are handled.
     *
     * Allowed values are Ignore or Fail. Defaults to Fail.
     */
    val failurePolicy: String? = null,
    /**
     * matchConditions is a list of conditions that must be met for a request to be validated. Match conditions
     * filter requests that have already been matched by the matchConstraints. An empty list of matchConditions
     * matches all requests. There are a maximum of 64 match conditions allowed.
     *
     * If a parameter object is provided, it can be accessed via the params handle in the same manner as
     * validation expressions.
     *
     * The exact matching logic is (in order):
     * 1. If ANY matchCondition evaluates to FALSE, the policy is skipped.
     * 2. If ALL matchConditions evaluate to TRUE, the policy is evaluated.
     * 3. If any matchCondition evaluates to an error (but none are FALSE):
     *    - If failurePolicy=Fail, reject the request
     *    - If failurePolicy=Ignore, the policy is skipped
     */
    val matchConditions: List<MatchCondition>? = null,
    /**
     * matchConstraints specifies what resources this policy is designed to validate. The MutatingAdmissionPolicy
     * cares about a request if it matches all Constraints. However, in order to prevent clusters from being put
     * into an unstable state that cannot be recovered from via the API MutatingAdmissionPolicy cannot match
     * MutatingAdmissionPolicy and MutatingAdmissionPolicyBinding. The CREATE, UPDATE and CONNECT operations are
     * allowed. The DELETE operation may not be matched. '*' matches CREATE, UPDATE and CONNECT. Required.
     */
    val matchConstraints: MatchResources? = null,
    /**
     * mutations contain operations to perform on matching objects. mutations may not be empty; a minimum of one
     * mutation is required. mutations are evaluated in order, and are reinvoked according to the reinvocationPolicy.
     * The mutations of a policy are invoked for each binding of this policy and reinvocation of mutations occurs
     * on a per binding basis.
     */
    val mutations: List<Mutation>? = null,
    /**
     * paramKind specifies the kind of resources used to parameterize this policy. If absent, there are no parameters
     * for this policy and the param CEL variable will not be provided to validation expressions. If paramKind refers
     * to a non-existent kind, this policy definition is mis-configured and the FailurePolicy is applied. If paramKind
     * is specified but paramRef is unset in MutatingAdmissionPolicyBinding, the params variable will be null.
     */
    val paramKind: ParamKind? = null,
    /**
     * reinvocationPolicy indicates whether mutations may be called multiple times per MutatingAdmissionPolicyBinding
     * as part of a single admission evaluation. Allowed values are "Never" and "IfNeeded".
     *
     * Never: These mutations will not be called more than once per binding in a single admission evaluation.
     *
     * IfNeeded: These mutations may be invoked more than once per binding for a single admission request and there
     * is no guarantee of order with respect to other admission plugins, admission webhooks, bindings of this policy
     * and admission policies. Mutations are only reinvoked when mutations change the object after this mutation is
     * invoked. Required.
     */
    val reinvocationPolicy: String? = null,
    /**
     * variables contain definitions of variables that can be used in composition of other expressions. Each variable
     * is defined as a named CEL expression. The variables defined here will be available under variables in other
     * expressions of the policy except matchConditions because matchConditions are evaluated before the rest of
     * the policy.
     *
     * The expression of a variable can refer to other variables defined earlier in the list but not those after.
     * Thus, variables must be sorted by the order of first appearance and acyclic.
     */
    val variables: List<Variable>? = null
)