
# Working Specifications

## Instruct

I want to build out objects with a specific format.
They should utilize `GeneratedDsl` and `DefaultValue` annotations. These items represent top
level Kubernetes resources, and should be structured to include the `apiVersion`, `metadata`, and
any specific fields relevant to the resource. Refer to the example below for guidance.

If there is an object that is a list in a property of another, add `withListGroup = true` to the `GeneratedDsl` annotation.

Additionally, you will have to look at the package io.violabs.picard.domain.k8sResources.APIVersion to
add the V2 version as an extension of the existing version.

Finally, update the v1 to have `@Deprecated("Use v2", ReplaceWith(<package for v2>))`


## Documentation

ValidatingAdmissionPolicy
ValidatingAdmissionPolicy describes the definition of an admission validation policy that accepts or rejects an object without changing it.
apiVersion: admissionregistration.k8s.io/v1

import "k8s.io/api/admissionregistration/v1"

ValidatingAdmissionPolicy
ValidatingAdmissionPolicy describes the definition of an admission validation policy that accepts or rejects an object without changing it.

apiVersion: admissionregistration.k8s.io/v1

kind: ValidatingAdmissionPolicy

metadata (ObjectMeta)

Standard object metadata; More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata.

spec (ValidatingAdmissionPolicySpec)

Specification of the desired behavior of the ValidatingAdmissionPolicy.

ValidatingAdmissionPolicySpec is the specification of the desired behavior of the AdmissionPolicy.

spec.auditAnnotations ([]AuditAnnotation)

Atomic: will be replaced during a merge

auditAnnotations contains CEL expressions which are used to produce audit annotations for the audit event of the API request. validations and auditAnnotations may not both be empty; a least one of validations or auditAnnotations is required.

AuditAnnotation describes how to produce an audit annotation for an API request.

spec.auditAnnotations.key (string), required

key specifies the audit annotation key. The audit annotation keys of a ValidatingAdmissionPolicy must be unique. The key must be a qualified name ([A-Za-z0-9][-A-Za-z0-9_.]*) no more than 63 bytes in length.

The key is combined with the resource name of the ValidatingAdmissionPolicy to construct an audit annotation key: "{ValidatingAdmissionPolicy name}/{key}".

If an admission webhook uses the same resource name as this ValidatingAdmissionPolicy and the same audit annotation key, the annotation key will be identical. In this case, the first annotation written with the key will be included in the audit event and all subsequent annotations with the same key will be discarded.

Required.

spec.auditAnnotations.valueExpression (string), required

valueExpression represents the expression which is evaluated by CEL to produce an audit annotation value. The expression must evaluate to either a string or null value. If the expression evaluates to a string, the audit annotation is included with the string value. If the expression evaluates to null or empty string the audit annotation will be omitted. The valueExpression may be no longer than 5kb in length. If the result of the valueExpression is more than 10kb in length, it will be truncated to 10kb.

If multiple ValidatingAdmissionPolicyBinding resources match an API request, then the valueExpression will be evaluated for each binding. All unique values produced by the valueExpressions will be joined together in a comma-separated list.

Required.

spec.failurePolicy (string)

failurePolicy defines how to handle failures for the admission policy. Failures can occur from CEL expression parse errors, type check errors, runtime errors and invalid or mis-configured policy definitions or bindings.

A policy is invalid if spec.paramKind refers to a non-existent Kind. A binding is invalid if spec.paramRef.name refers to a non-existent resource.

failurePolicy does not define how validations that evaluate to false are handled.

When failurePolicy is set to Fail, ValidatingAdmissionPolicyBinding validationActions define how failures are enforced.

Allowed values are Ignore or Fail. Defaults to Fail.

spec.matchConditions ([]MatchCondition)

Patch strategy: merge on key name

Map: unique values on key name will be kept during a merge

MatchConditions is a list of conditions that must be met for a request to be validated. Match conditions filter requests that have already been matched by the rules, namespaceSelector, and objectSelector. An empty list of matchConditions matches all requests. There are a maximum of 64 match conditions allowed.

If a parameter object is provided, it can be accessed via the params handle in the same manner as validation expressions.

The exact matching logic is (in order):

If ANY matchCondition evaluates to FALSE, the policy is skipped.
If ALL matchConditions evaluate to TRUE, the policy is evaluated.
If any matchCondition evaluates to an error (but none are FALSE):
If failurePolicy=Fail, reject the request
If failurePolicy=Ignore, the policy is skipped
MatchCondition represents a condition which must by fulfilled for a request to be sent to a webhook.

spec.matchConditions.expression (string), required

Expression represents the expression which will be evaluated by CEL. Must evaluate to bool. CEL expressions have access to the contents of the AdmissionRequest and Authorizer, organized into CEL variables:

'object' - The object from the incoming request. The value is null for DELETE requests. 'oldObject' - The existing object. The value is null for CREATE requests. 'request' - Attributes of the admission request(/pkg/apis/admission/types.go#AdmissionRequest). 'authorizer' - A CEL Authorizer. May be used to perform authorization checks for the principal (user or service account) of the request. See https://pkg.go.dev/k8s.io/apiserver/pkg/cel/library#Authz 'authorizer.requestResource' - A CEL ResourceCheck constructed from the 'authorizer' and configured with the request resource. Documentation on CEL: https://kubernetes.io/docs/reference/using-api/cel/

Required.

spec.matchConditions.name (string), required

Name is an identifier for this match condition, used for strategic merging of MatchConditions, as well as providing an identifier for logging purposes. A good name should be descriptive of the associated expression. Name must be a qualified name consisting of alphanumeric characters, '-', '' or '.', and must start and end with an alphanumeric character (e.g. 'MyName', or 'my.name', or '123-abc', regex used for validation is '([A-Za-z0-9][-A-Za-z0-9.]*)?[A-Za-z0-9]') with an optional DNS subdomain prefix and '/' (e.g. 'example.com/MyName')

Required.

spec.matchConstraints (MatchResources)

MatchConstraints specifies what resources this policy is designed to validate. The AdmissionPolicy cares about a request if it matches all Constraints. However, in order to prevent clusters from being put into an unstable state that cannot be recovered from via the API ValidatingAdmissionPolicy cannot match ValidatingAdmissionPolicy and ValidatingAdmissionPolicyBinding. Required.

MatchResources decides whether to run the admission control policy on an object based on whether it meets the match criteria. The exclude rules take precedence over include rules (if a resource matches both, it is excluded)

spec.matchConstraints.excludeResourceRules ([]NamedRuleWithOperations)

Atomic: will be replaced during a merge

ExcludeResourceRules describes what operations on what resources/subresources the ValidatingAdmissionPolicy should not care about. The exclude rules take precedence over include rules (if a resource matches both, it is excluded)

NamedRuleWithOperations is a tuple of Operations and Resources with ResourceNames.

spec.matchConstraints.excludeResourceRules.apiGroups ([]string)

Atomic: will be replaced during a merge

APIGroups is the API groups the resources belong to. '' is all groups. If '' is present, the length of the slice must be one. Required.

spec.matchConstraints.excludeResourceRules.apiVersions ([]string)

Atomic: will be replaced during a merge

APIVersions is the API versions the resources belong to. '' is all versions. If '' is present, the length of the slice must be one. Required.

spec.matchConstraints.excludeResourceRules.operations ([]string)

Atomic: will be replaced during a merge

Operations is the operations the admission hook cares about - CREATE, UPDATE, DELETE, CONNECT or * for all of those operations and any future admission operations that are added. If '*' is present, the length of the slice must be one. Required.

spec.matchConstraints.excludeResourceRules.resourceNames ([]string)

Atomic: will be replaced during a merge

ResourceNames is an optional white list of names that the rule applies to. An empty set means that everything is allowed.

spec.matchConstraints.excludeResourceRules.resources ([]string)

Atomic: will be replaced during a merge

Resources is a list of resources this rule applies to.

For example: 'pods' means pods. 'pods/log' means the log subresource of pods. '' means all resources, but not subresources. 'pods/' means all subresources of pods. '/scale' means all scale subresources. '/*' means all resources and their subresources.

If wildcard is present, the validation rule will ensure resources do not overlap with each other.

Depending on the enclosing object, subresources might not be allowed. Required.

spec.matchConstraints.excludeResourceRules.scope (string)

scope specifies the scope of this rule. Valid values are "Cluster", "Namespaced", and "" "Cluster" means that only cluster-scoped resources will match this rule. Namespace API objects are cluster-scoped. "Namespaced" means that only namespaced resources will match this rule. "" means that there are no scope restrictions. Subresources match the scope of their parent resource. Default is "*".

spec.matchConstraints.matchPolicy (string)

matchPolicy defines how the "MatchResources" list is used to match incoming requests. Allowed values are "Exact" or "Equivalent".

Exact: match a request only if it exactly matches a specified rule. For example, if deployments can be modified via apps/v1, apps/v1beta1, and extensions/v1beta1, but "rules" only included apiGroups:["apps"], apiVersions:["v1"], resources: ["deployments"], a request to apps/v1beta1 or extensions/v1beta1 would not be sent to the ValidatingAdmissionPolicy.

Equivalent: match a request if modifies a resource listed in rules, even via another API group or version. For example, if deployments can be modified via apps/v1, apps/v1beta1, and extensions/v1beta1, and "rules" only included apiGroups:["apps"], apiVersions:["v1"], resources: ["deployments"], a request to apps/v1beta1 or extensions/v1beta1 would be converted to apps/v1 and sent to the ValidatingAdmissionPolicy.

Defaults to "Equivalent"

spec.matchConstraints.namespaceSelector (LabelSelector)

NamespaceSelector decides whether to run the admission control policy on an object based on whether the namespace for that object matches the selector. If the object itself is a namespace, the matching is performed on object.metadata.labels. If the object is another cluster scoped resource, it never skips the policy.

For example, to run the webhook on any objects whose namespace is not associated with "runlevel" of "0" or "1"; you will set the selector as follows: "namespaceSelector": { "matchExpressions": [ { "key": "runlevel", "operator": "NotIn", "values": [ "0", "1" ] } ] }

If instead you want to only run the policy on any objects whose namespace is associated with the "environment" of "prod" or "staging"; you will set the selector as follows: "namespaceSelector": { "matchExpressions": [ { "key": "environment", "operator": "In", "values": [ "prod", "staging" ] } ] }

See https://kubernetes.io/docs/concepts/overview/working-with-objects/labels/ for more examples of label selectors.

Default to the empty LabelSelector, which matches everything.

spec.matchConstraints.objectSelector (LabelSelector)

ObjectSelector decides whether to run the validation based on if the object has matching labels. objectSelector is evaluated against both the oldObject and newObject that would be sent to the cel validation, and is considered to match if either object matches the selector. A null object (oldObject in the case of create, or newObject in the case of delete) or an object that cannot have labels (like a DeploymentRollback or a PodProxyOptions object) is not considered to match. Use the object selector only if the webhook is opt-in, because end users may skip the admission webhook by setting the labels. Default to the empty LabelSelector, which matches everything.

spec.matchConstraints.resourceRules ([]NamedRuleWithOperations)

Atomic: will be replaced during a merge

ResourceRules describes what operations on what resources/subresources the ValidatingAdmissionPolicy matches. The policy cares about an operation if it matches any Rule.

NamedRuleWithOperations is a tuple of Operations and Resources with ResourceNames.

spec.matchConstraints.resourceRules.apiGroups ([]string)

Atomic: will be replaced during a merge

APIGroups is the API groups the resources belong to. '' is all groups. If '' is present, the length of the slice must be one. Required.

spec.matchConstraints.resourceRules.apiVersions ([]string)

Atomic: will be replaced during a merge

APIVersions is the API versions the resources belong to. '' is all versions. If '' is present, the length of the slice must be one. Required.

spec.matchConstraints.resourceRules.operations ([]string)

Atomic: will be replaced during a merge

Operations is the operations the admission hook cares about - CREATE, UPDATE, DELETE, CONNECT or * for all of those operations and any future admission operations that are added. If '*' is present, the length of the slice must be one. Required.

spec.matchConstraints.resourceRules.resourceNames ([]string)

Atomic: will be replaced during a merge

ResourceNames is an optional white list of names that the rule applies to. An empty set means that everything is allowed.

spec.matchConstraints.resourceRules.resources ([]string)

Atomic: will be replaced during a merge

Resources is a list of resources this rule applies to.

For example: 'pods' means pods. 'pods/log' means the log subresource of pods. '' means all resources, but not subresources. 'pods/' means all subresources of pods. '/scale' means all scale subresources. '/*' means all resources and their subresources.

If wildcard is present, the validation rule will ensure resources do not overlap with each other.

Depending on the enclosing object, subresources might not be allowed. Required.

spec.matchConstraints.resourceRules.scope (string)

scope specifies the scope of this rule. Valid values are "Cluster", "Namespaced", and "" "Cluster" means that only cluster-scoped resources will match this rule. Namespace API objects are cluster-scoped. "Namespaced" means that only namespaced resources will match this rule. "" means that there are no scope restrictions. Subresources match the scope of their parent resource. Default is "*".

spec.paramKind (ParamKind)

ParamKind specifies the kind of resources used to parameterize this policy. If absent, there are no parameters for this policy and the param CEL variable will not be provided to validation expressions. If ParamKind refers to a non-existent kind, this policy definition is mis-configured and the FailurePolicy is applied. If paramKind is specified but paramRef is unset in ValidatingAdmissionPolicyBinding, the params variable will be null.

ParamKind is a tuple of Group Kind and Version.

spec.paramKind.apiVersion (string)

APIVersion is the API group version the resources belong to. In format of "group/version". Required.

spec.paramKind.kind (string)

Kind is the API kind the resources belong to. Required.

spec.validations ([]Validation)

Atomic: will be replaced during a merge

Validations contain CEL expressions which is used to apply the validation. Validations and AuditAnnotations may not both be empty; a minimum of one Validations or AuditAnnotations is required.

Validation specifies the CEL expression which is used to apply the validation.

spec.validations.expression (string), required

Expression represents the expression which will be evaluated by CEL. ref: https://github.com/google/cel-spec CEL expressions have access to the contents of the API request/response, organized into CEL variables as well as some other useful variables:

'object' - The object from the incoming request. The value is null for DELETE requests. - 'oldObject' - The existing object. The value is null for CREATE requests. - 'request' - Attributes of the API request(ref). - 'params' - Parameter resource referred to by the policy binding being evaluated. Only populated if the policy has a ParamKind. - 'namespaceObject' - The namespace object that the incoming object belongs to. The value is null for cluster-scoped resources. - 'variables' - Map of composited variables, from its name to its lazily evaluated value. For example, a variable named 'foo' can be accessed as 'variables.foo'.
'authorizer' - A CEL Authorizer. May be used to perform authorization checks for the principal (user or service account) of the request. See https://pkg.go.dev/k8s.io/apiserver/pkg/cel/library#Authz
'authorizer.requestResource' - A CEL ResourceCheck constructed from the 'authorizer' and configured with the request resource.
The apiVersion, kind, metadata.name and metadata.generateName are always accessible from the root of the object. No other metadata properties are accessible.

Only property names of the form [a-zA-Z_.-/][a-zA-Z0-9_.-/]* are accessible. Accessible property names are escaped according to the following rules when accessed in the expression: - '' escapes to 'underscores' - '.' escapes to 'dot' - '-' escapes to 'dash' - '/' escapes to 'slash' - Property names that exactly match a CEL RESERVED keyword escape to '{keyword}__'. The keywords are: "true", "false", "null", "in", "as", "break", "const", "continue", "else", "for", "function", "if", "import", "let", "loop", "package", "namespace", "return". Examples:

Expression accessing a property named "namespace": {"Expression": "object.namespace > 0"}
Expression accessing a property named "x-prop": {"Expression": "object.x__dash__prop > 0"}
Expression accessing a property named "redact__d": {"Expression": "object.redact__underscores__d > 0"}
Equality on arrays with list type of 'set' or 'map' ignores element order, i.e. [1, 2] == [2, 1]. Concatenation on arrays with x-kubernetes-list-type use the semantics of the list type:

'set': X + Y performs a union where the array positions of all elements in X are preserved and non-intersecting elements in Y are appended, retaining their partial order.
'map': X + Y performs a merge where the array positions of all keys in X are preserved but the values are overwritten by values in Y when the key sets of X and Y intersect. Elements in Y with non-intersecting keys are appended, retaining their partial order. Required.
spec.validations.message (string)

Message represents the message displayed when validation fails. The message is required if the Expression contains line breaks. The message must not contain line breaks. If unset, the message is "failed rule: {Rule}". e.g. "must be a URL with the host matching spec.host" If the Expression contains line breaks. Message is required. The message must not contain line breaks. If unset, the message is "failed Expression: {Expression}".

spec.validations.messageExpression (string)

messageExpression declares a CEL expression that evaluates to the validation failure message that is returned when this rule fails. Since messageExpression is used as a failure message, it must evaluate to a string. If both message and messageExpression are present on a validation, then messageExpression will be used if validation fails. If messageExpression results in a runtime error, the runtime error is logged, and the validation failure message is produced as if the messageExpression field were unset. If messageExpression evaluates to an empty string, a string with only spaces, or a string that contains line breaks, then the validation failure message will also be produced as if the messageExpression field were unset, and the fact that messageExpression produced an empty string/string with only spaces/string with line breaks will be logged. messageExpression has access to all the same variables as the expression except for 'authorizer' and 'authorizer.requestResource'. Example: "object.x must be less than max ("+string(params.max)+")"

spec.validations.reason (string)

Reason represents a machine-readable description of why this validation failed. If this is the first validation in the list to fail, this reason, as well as the corresponding HTTP response code, are used in the HTTP response to the client. The currently supported reasons are: "Unauthorized", "Forbidden", "Invalid", "RequestEntityTooLarge". If not set, StatusReasonInvalid is used in the response to the client.

spec.variables ([]Variable)

Patch strategy: merge on key name

Map: unique values on key name will be kept during a merge

Variables contain definitions of variables that can be used in composition of other expressions. Each variable is defined as a named CEL expression. The variables defined here will be available under variables in other expressions of the policy except MatchConditions because MatchConditions are evaluated before the rest of the policy.

The expression of a variable can refer to other variables defined earlier in the list but not those after. Thus, Variables must be sorted by the order of first appearance and acyclic.

Variable is the definition of a variable that is used for composition. A variable is defined as a named expression.

spec.variables.expression (string), required

Expression is the expression that will be evaluated as the value of the variable. The CEL expression has access to the same identifiers as the CEL expressions in Validation.

spec.variables.name (string), required

Name is the name of the variable. The name must be a valid CEL identifier and unique among all variables. The variable can be accessed in other expressions through variables For example, if name is "foo", the variable will be available as variables.foo

status (ValidatingAdmissionPolicyStatus)

The status of the ValidatingAdmissionPolicy, including warnings that are useful to determine if the policy behaves in the expected way. Populated by the system. Read-only.

ValidatingAdmissionPolicyStatus represents the status of an admission validation policy.

status.conditions ([]Condition)

Map: unique values on key type will be kept during a merge

The conditions represent the latest available observations of a policy's current state.

Condition contains details for one aspect of the current state of this API Resource.

status.conditions.lastTransitionTime (Time), required

lastTransitionTime is the last time the condition transitioned from one status to another. This should be when the underlying condition changed. If that is not known, then using the time when the API field changed is acceptable.

Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON. Wrappers are provided for many of the factory methods that the time package offers.

status.conditions.message (string), required

message is a human readable message indicating details about the transition. This may be an empty string.

status.conditions.reason (string), required

reason contains a programmatic identifier indicating the reason for the condition's last transition. Producers of specific condition types may define expected values and meanings for this field, and whether the values are considered a guaranteed API. The value should be a CamelCase string. This field may not be empty.

status.conditions.status (string), required

status of the condition, one of True, False, Unknown.

status.conditions.type (string), required

type of condition in CamelCase or in foo.example.com/CamelCase.

status.conditions.observedGeneration (int64)

observedGeneration represents the .metadata.generation that the condition was set based upon. For instance, if .metadata.generation is currently 12, but the .status.conditions[x].observedGeneration is 9, the condition is out of date with respect to the current state of the instance.

status.observedGeneration (int64)

The generation observed by the controller.

status.typeChecking (TypeChecking)

The results of type checking for each expression. Presence of this field indicates the completion of the type checking.

TypeChecking contains results of type checking the expressions in the ValidatingAdmissionPolicy

status.typeChecking.expressionWarnings ([]ExpressionWarning)

Atomic: will be replaced during a merge

The type checking warnings for each expression.

ExpressionWarning is a warning information that targets a specific expression.

status.typeChecking.expressionWarnings.fieldRef (string), required

The path to the field that refers the expression. For example, the reference to the expression of the first item of validations is "spec.validations[0].expression"

status.typeChecking.expressionWarnings.warning (string), required

The content of type checking information in a human-readable form. Each line of the warning contains the type that the expression is checked against, followed by the type check error from the compiler.



## Example

```kotlin
package io.violabs.picard.v2.resources.authentication.certificate

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthenticationResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/authentication-resources/certificate-signing-request-v1/
 *
 * CertificateSigningRequest objects provide a mechanism to obtain x509
 * certificates by submitting a certificate signing request, and having it
 * asynchronously approved and issued.
 *
 * Kubelets use this API to obtain:
 *
 * 1. client certificates to authenticate to kube-apiserver
 *    (with the "kubernetes.io/kube-apiserver-client-kubelet" signerName).
 * 2. serving certificates for TLS endpoints kube-apiserver can connect to securely
 *    (with the "kubernetes.io/kubelet-serving" signerName).
 *
 * This API can be used to request client certificates to authenticate to kube-apiserver
 * (with the "kubernetes.io/kube-apiserver-client" signerName), or to obtain
 * certificates from custom non-Kubernetes signers.
 *
 * apiVersion: certificates.k8s.io/v1
 *
 * import "k8s.io/api/certificates/v1"
 */
@GeneratedDsl
data class CertificateSigningRequestV2(
    @DefaultValue(
        "KAPIVersion.CertificatesV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.CertificatesV1,
    override val metadata: ObjectMeta? = null,
    /**
     * spec contains the certificate request, and is immutable after creation.
     * Only the request, signerName, expirationSeconds, and usages fields can be set on creation.
     * Other fields are derived by Kubernetes and cannot be modified by users.
     */
    val spec: CertificateSigningRequestSpec,
    /**
     * status contains information about whether the request is approved or denied, and the
     * certificate issued by the signer, or the failure condition indicating signer failure.
     */
    val status: CertificateSigningRequestStatus? = null
) : AuthenticationResource<CertificateSigningRequestV2.Version, ObjectMeta> {
    interface Version : APIVersion
}

```