
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

MutatingAdmissionPolicy v1alpha1
MutatingAdmissionPolicy describes the definition of an admission mutation policy that mutates the object coming into admission chain.
apiVersion: admissionregistration.k8s.io/v1alpha1

import "k8s.io/api/admissionregistration/v1alpha1"

MutatingAdmissionPolicy
MutatingAdmissionPolicy describes the definition of an admission mutation policy that mutates the object coming into admission chain.

apiVersion: admissionregistration.k8s.io/v1alpha1

kind: MutatingAdmissionPolicy

metadata (ObjectMeta)

Standard object metadata; More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata.

spec (MutatingAdmissionPolicySpec)

Specification of the desired behavior of the MutatingAdmissionPolicy.

MutatingAdmissionPolicySpec is the specification of the desired behavior of the admission policy.

spec.failurePolicy (string)

failurePolicy defines how to handle failures for the admission policy. Failures can occur from CEL expression parse errors, type check errors, runtime errors and invalid or mis-configured policy definitions or bindings.

A policy is invalid if paramKind refers to a non-existent Kind. A binding is invalid if paramRef.name refers to a non-existent resource.

failurePolicy does not define how validations that evaluate to false are handled.

Allowed values are Ignore or Fail. Defaults to Fail.

spec.matchConditions ([]MatchCondition)

Patch strategy: merge on key name

Map: unique values on key name will be kept during a merge

matchConditions is a list of conditions that must be met for a request to be validated. Match conditions filter requests that have already been matched by the matchConstraints. An empty list of matchConditions matches all requests. There are a maximum of 64 match conditions allowed.

If a parameter object is provided, it can be accessed via the params handle in the same manner as validation expressions.

The exact matching logic is (in order):

If ANY matchCondition evaluates to FALSE, the policy is skipped.
If ALL matchConditions evaluate to TRUE, the policy is evaluated.
If any matchCondition evaluates to an error (but none are FALSE):
If failurePolicy=Fail, reject the request
If failurePolicy=Ignore, the policy is skipped
**

spec.matchConditions.expression (string), required

Expression represents the expression which will be evaluated by CEL. Must evaluate to bool. CEL expressions have access to the contents of the AdmissionRequest and Authorizer, organized into CEL variables:

'object' - The object from the incoming request. The value is null for DELETE requests. 'oldObject' - The existing object. The value is null for CREATE requests. 'request' - Attributes of the admission request(/pkg/apis/admission/types.go#AdmissionRequest). 'authorizer' - A CEL Authorizer. May be used to perform authorization checks for the principal (user or service account) of the request. See https://pkg.go.dev/k8s.io/apiserver/pkg/cel/library#Authz 'authorizer.requestResource' - A CEL ResourceCheck constructed from the 'authorizer' and configured with the request resource. Documentation on CEL: https://kubernetes.io/docs/reference/using-api/cel/

Required.

spec.matchConditions.name (string), required

Name is an identifier for this match condition, used for strategic merging of MatchConditions, as well as providing an identifier for logging purposes. A good name should be descriptive of the associated expression. Name must be a qualified name consisting of alphanumeric characters, '-', '' or '.', and must start and end with an alphanumeric character (e.g. 'MyName', or 'my.name', or '123-abc', regex used for validation is '([A-Za-z0-9][-A-Za-z0-9.]*)?[A-Za-z0-9]') with an optional DNS subdomain prefix and '/' (e.g. 'example.com/MyName')

Required.

spec.matchConstraints (MatchResources)

matchConstraints specifies what resources this policy is designed to validate. The MutatingAdmissionPolicy cares about a request if it matches all Constraints. However, in order to prevent clusters from being put into an unstable state that cannot be recovered from via the API MutatingAdmissionPolicy cannot match MutatingAdmissionPolicy and MutatingAdmissionPolicyBinding. The CREATE, UPDATE and CONNECT operations are allowed. The DELETE operation may not be matched. '*' matches CREATE, UPDATE and CONNECT. Required.

MatchResources decides whether to run the admission control policy on an object based on whether it meets the match criteria. The exclude rules take precedence over include rules (if a resource matches both, it is excluded)

spec.matchConstraints.excludeResourceRules ([]NamedRuleWithOperations)

Atomic: will be replaced during a merge

ExcludeResourceRules describes what operations on what resources/subresources the policy should not care about. The exclude rules take precedence over include rules (if a resource matches both, it is excluded)

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

Exact: match a request only if it exactly matches a specified rule. For example, if deployments can be modified via apps/v1, apps/v1beta1, and extensions/v1beta1, but "rules" only included apiGroups:["apps"], apiVersions:["v1"], resources: ["deployments"], the admission policy does not consider requests to apps/v1beta1 or extensions/v1beta1 API groups.

Equivalent: match a request if modifies a resource listed in rules, even via another API group or version. For example, if deployments can be modified via apps/v1, apps/v1beta1, and extensions/v1beta1, and "rules" only included apiGroups:["apps"], apiVersions:["v1"], resources: ["deployments"], the admission policy does consider requests made to apps/v1beta1 or extensions/v1beta1 API groups. The API server translates the request to a matched resource API if necessary.

Defaults to "Equivalent"

spec.matchConstraints.namespaceSelector (LabelSelector)

NamespaceSelector decides whether to run the admission control policy on an object based on whether the namespace for that object matches the selector. If the object itself is a namespace, the matching is performed on object.metadata.labels. If the object is another cluster scoped resource, it never skips the policy.

For example, to run the webhook on any objects whose namespace is not associated with "runlevel" of "0" or "1"; you will set the selector as follows: "namespaceSelector": { "matchExpressions": [ { "key": "runlevel", "operator": "NotIn", "values": [ "0", "1" ] } ] }

If instead you want to only run the policy on any objects whose namespace is associated with the "environment" of "prod" or "staging"; you will set the selector as follows: "namespaceSelector": { "matchExpressions": [ { "key": "environment", "operator": "In", "values": [ "prod", "staging" ] } ] }

See https://kubernetes.io/docs/concepts/overview/working-with-objects/labels/ for more examples of label selectors.

Default to the empty LabelSelector, which matches everything.

spec.matchConstraints.objectSelector (LabelSelector)

ObjectSelector decides whether to run the policy based on if the object has matching labels. objectSelector is evaluated against both the oldObject and newObject that would be sent to the policy's expression (CEL), and is considered to match if either object matches the selector. A null object (oldObject in the case of create, or newObject in the case of delete) or an object that cannot have labels (like a DeploymentRollback or a PodProxyOptions object) is not considered to match. Use the object selector only if the webhook is opt-in, because end users may skip the admission webhook by setting the labels. Default to the empty LabelSelector, which matches everything.

spec.matchConstraints.resourceRules ([]NamedRuleWithOperations)

Atomic: will be replaced during a merge

ResourceRules describes what operations on what resources/subresources the admission policy matches. The policy cares about an operation if it matches any Rule.

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

spec.mutations ([]Mutation)

Atomic: will be replaced during a merge

mutations contain operations to perform on matching objects. mutations may not be empty; a minimum of one mutation is required. mutations are evaluated in order, and are reinvoked according to the reinvocationPolicy. The mutations of a policy are invoked for each binding of this policy and reinvocation of mutations occurs on a per binding basis.

Mutation specifies the CEL expression which is used to apply the Mutation.

spec.mutations.patchType (string), required

patchType indicates the patch strategy used. Allowed values are "ApplyConfiguration" and "JSONPatch". Required.

spec.mutations.applyConfiguration (ApplyConfiguration)

applyConfiguration defines the desired configuration values of an object. The configuration is applied to the admission object using structured merge diff. A CEL expression is used to create apply configuration.

ApplyConfiguration defines the desired configuration values of an object.

spec.mutations.applyConfiguration.expression (string)

expression will be evaluated by CEL to create an apply configuration. ref: https://github.com/google/cel-spec

Apply configurations are declared in CEL using object initialization. For example, this CEL expression returns an apply configuration to set a single field:

Object{
spec: Object.spec{
serviceAccountName: "example"
}
}
Apply configurations may not modify atomic structs, maps or arrays due to the risk of accidental deletion of values not included in the apply configuration.

CEL expressions have access to the object types needed to create apply configurations:

'Object' - CEL type of the resource object. - 'Object.<fieldName>' - CEL type of object field (such as 'Object.spec') - 'Object.<fieldName1>.<fieldName2>...<fieldNameN>` - CEL type of nested field (such as 'Object.spec.containers')
CEL expressions have access to the contents of the API request, organized into CEL variables as well as some other useful variables:

'object' - The object from the incoming request. The value is null for DELETE requests. - 'oldObject' - The existing object. The value is null for CREATE requests. - 'request' - Attributes of the API request(ref). - 'params' - Parameter resource referred to by the policy binding being evaluated. Only populated if the policy has a ParamKind. - 'namespaceObject' - The namespace object that the incoming object belongs to. The value is null for cluster-scoped resources. - 'variables' - Map of composited variables, from its name to its lazily evaluated value. For example, a variable named 'foo' can be accessed as 'variables.foo'.
'authorizer' - A CEL Authorizer. May be used to perform authorization checks for the principal (user or service account) of the request. See https://pkg.go.dev/k8s.io/apiserver/pkg/cel/library#Authz
'authorizer.requestResource' - A CEL ResourceCheck constructed from the 'authorizer' and configured with the request resource.
The apiVersion, kind, metadata.name and metadata.generateName are always accessible from the root of the object. No other metadata properties are accessible.

Only property names of the form [a-zA-Z_.-/][a-zA-Z0-9_.-/]* are accessible. Required.

spec.mutations.jsonPatch (JSONPatch)

jsonPatch defines a JSON patch operation to perform a mutation to the object. A CEL expression is used to create the JSON patch.

JSONPatch defines a JSON Patch.

spec.mutations.jsonPatch.expression (string)

expression will be evaluated by CEL to create a JSON patch. ref: https://github.com/google/cel-spec

expression must return an array of JSONPatch values.

For example, this CEL expression returns a JSON patch to conditionally modify a value:

[
JSONPatch{op: "test", path: "/spec/example", value: "Red"},
JSONPatch{op: "replace", path: "/spec/example", value: "Green"}
]
To define an object for the patch value, use Object types. For example:

[
JSONPatch{
op: "add",
path: "/spec/selector",
value: Object.spec.selector{matchLabels: {"environment": "test"}}
}
]
To use strings containing '/' and '~' as JSONPatch path keys, use "jsonpatch.escapeKey". For example:

[
JSONPatch{
op: "add",
path: "/metadata/labels/" + jsonpatch.escapeKey("example.com/environment"),
value: "test"
},
]
CEL expressions have access to the types needed to create JSON patches and objects:

'JSONPatch' - CEL type of JSON Patch operations. JSONPatch has the fields 'op', 'from', 'path' and 'value'. See JSON patch for more details. The 'value' field may be set to any of: string, integer, array, map or object. If set, the 'path' and 'from' fields must be set to a JSON pointer string, where the 'jsonpatch.escapeKey()' CEL function may be used to escape path keys containing '/' and '~'.
'Object' - CEL type of the resource object. - 'Object.<fieldName>' - CEL type of object field (such as 'Object.spec') - 'Object.<fieldName1>.<fieldName2>...<fieldNameN>` - CEL type of nested field (such as 'Object.spec.containers')
CEL expressions have access to the contents of the API request, organized into CEL variables as well as some other useful variables:

'object' - The object from the incoming request. The value is null for DELETE requests. - 'oldObject' - The existing object. The value is null for CREATE requests. - 'request' - Attributes of the API request(ref). - 'params' - Parameter resource referred to by the policy binding being evaluated. Only populated if the policy has a ParamKind. - 'namespaceObject' - The namespace object that the incoming object belongs to. The value is null for cluster-scoped resources. - 'variables' - Map of composited variables, from its name to its lazily evaluated value. For example, a variable named 'foo' can be accessed as 'variables.foo'.
'authorizer' - A CEL Authorizer. May be used to perform authorization checks for the principal (user or service account) of the request. See https://pkg.go.dev/k8s.io/apiserver/pkg/cel/library#Authz
'authorizer.requestResource' - A CEL ResourceCheck constructed from the 'authorizer' and configured with the request resource.
CEL expressions have access to Kubernetes CEL function libraries as well as:

'jsonpatch.escapeKey' - Performs JSONPatch key escaping. '~' and '/' are escaped as '~0' and `~1' respectively).
Only property names of the form [a-zA-Z_.-/][a-zA-Z0-9_.-/]* are accessible. Required.

spec.paramKind (ParamKind)

paramKind specifies the kind of resources used to parameterize this policy. If absent, there are no parameters for this policy and the param CEL variable will not be provided to validation expressions. If paramKind refers to a non-existent kind, this policy definition is mis-configured and the FailurePolicy is applied. If paramKind is specified but paramRef is unset in MutatingAdmissionPolicyBinding, the params variable will be null.

ParamKind is a tuple of Group Kind and Version.

spec.paramKind.apiVersion (string)

APIVersion is the API group version the resources belong to. In format of "group/version". Required.

spec.paramKind.kind (string)

Kind is the API kind the resources belong to. Required.

spec.reinvocationPolicy (string)

reinvocationPolicy indicates whether mutations may be called multiple times per MutatingAdmissionPolicyBinding as part of a single admission evaluation. Allowed values are "Never" and "IfNeeded".

Never: These mutations will not be called more than once per binding in a single admission evaluation.

IfNeeded: These mutations may be invoked more than once per binding for a single admission request and there is no guarantee of order with respect to other admission plugins, admission webhooks, bindings of this policy and admission policies. Mutations are only reinvoked when mutations change the object after this mutation is invoked. Required.

spec.variables ([]Variable)

Atomic: will be replaced during a merge

variables contain definitions of variables that can be used in composition of other expressions. Each variable is defined as a named CEL expression. The variables defined here will be available under variables in other expressions of the policy except matchConditions because matchConditions are evaluated before the rest of the policy.

The expression of a variable can refer to other variables defined earlier in the list but not those after. Thus, variables must be sorted by the order of first appearance and acyclic.

Variable is the definition of a variable that is used for composition.

spec.variables.expression (string), required

Expression is the expression that will be evaluated as the value of the variable. The CEL expression has access to the same identifiers as the CEL expressions in Validation.

spec.variables.name (string), required

Name is the name of the variable. The name must be a valid CEL identifier and unique among all variables. The variable can be accessed in other expressions through variables For example, if name is "foo", the variable will be available as variables.foo




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