
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

MutatingAdmissionPolicyBinding v1alpha1
MutatingAdmissionPolicyBinding binds the MutatingAdmissionPolicy with parametrized resources.
apiVersion: admissionregistration.k8s.io/v1alpha1

import "k8s.io/api/admissionregistration/v1alpha1"

MutatingAdmissionPolicyBinding
MutatingAdmissionPolicyBinding binds the MutatingAdmissionPolicy with parametrized resources. MutatingAdmissionPolicyBinding and the optional parameter resource together define how cluster administrators configure policies for clusters.

For a given admission request, each binding will cause its policy to be evaluated N times, where N is 1 for policies/bindings that don't use params, otherwise N is the number of parameters selected by the binding. Each evaluation is constrained by a runtime cost budget.

Adding/removing policies, bindings, or params can not affect whether a given (policy, binding, param) combination is within its own CEL budget.

apiVersion: admissionregistration.k8s.io/v1alpha1

kind: MutatingAdmissionPolicyBinding

metadata (ObjectMeta)

Standard object metadata; More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata.

spec (MutatingAdmissionPolicyBindingSpec)

Specification of the desired behavior of the MutatingAdmissionPolicyBinding.

MutatingAdmissionPolicyBindingSpec is the specification of the MutatingAdmissionPolicyBinding.

spec.matchResources (MatchResources)

matchResources limits what resources match this binding and may be mutated by it. Note that if matchResources matches a resource, the resource must also match a policy's matchConstraints and matchConditions before the resource may be mutated. When matchResources is unset, it does not constrain resource matching, and only the policy's matchConstraints and matchConditions must match for the resource to be mutated. Additionally, matchResources.resourceRules are optional and do not constraint matching when unset. Note that this is differs from MutatingAdmissionPolicy matchConstraints, where resourceRules are required. The CREATE, UPDATE and CONNECT operations are allowed. The DELETE operation may not be matched. '*' matches CREATE, UPDATE and CONNECT.

MatchResources decides whether to run the admission control policy on an object based on whether it meets the match criteria. The exclude rules take precedence over include rules (if a resource matches both, it is excluded)

spec.matchResources.excludeResourceRules ([]NamedRuleWithOperations)

Atomic: will be replaced during a merge

ExcludeResourceRules describes what operations on what resources/subresources the policy should not care about. The exclude rules take precedence over include rules (if a resource matches both, it is excluded)

NamedRuleWithOperations is a tuple of Operations and Resources with ResourceNames.

spec.matchResources.excludeResourceRules.apiGroups ([]string)

Atomic: will be replaced during a merge

APIGroups is the API groups the resources belong to. '' is all groups. If '' is present, the length of the slice must be one. Required.

spec.matchResources.excludeResourceRules.apiVersions ([]string)

Atomic: will be replaced during a merge

APIVersions is the API versions the resources belong to. '' is all versions. If '' is present, the length of the slice must be one. Required.

spec.matchResources.excludeResourceRules.operations ([]string)

Atomic: will be replaced during a merge

Operations is the operations the admission hook cares about - CREATE, UPDATE, DELETE, CONNECT or * for all of those operations and any future admission operations that are added. If '*' is present, the length of the slice must be one. Required.

spec.matchResources.excludeResourceRules.resourceNames ([]string)

Atomic: will be replaced during a merge

ResourceNames is an optional white list of names that the rule applies to. An empty set means that everything is allowed.

spec.matchResources.excludeResourceRules.resources ([]string)

Atomic: will be replaced during a merge

Resources is a list of resources this rule applies to.

For example: 'pods' means pods. 'pods/log' means the log subresource of pods. '' means all resources, but not subresources. 'pods/' means all subresources of pods. '/scale' means all scale subresources. '/*' means all resources and their subresources.

If wildcard is present, the validation rule will ensure resources do not overlap with each other.

Depending on the enclosing object, subresources might not be allowed. Required.

spec.matchResources.excludeResourceRules.scope (string)

scope specifies the scope of this rule. Valid values are "Cluster", "Namespaced", and "" "Cluster" means that only cluster-scoped resources will match this rule. Namespace API objects are cluster-scoped. "Namespaced" means that only namespaced resources will match this rule. "" means that there are no scope restrictions. Subresources match the scope of their parent resource. Default is "*".

spec.matchResources.matchPolicy (string)

matchPolicy defines how the "MatchResources" list is used to match incoming requests. Allowed values are "Exact" or "Equivalent".

Exact: match a request only if it exactly matches a specified rule. For example, if deployments can be modified via apps/v1, apps/v1beta1, and extensions/v1beta1, but "rules" only included apiGroups:["apps"], apiVersions:["v1"], resources: ["deployments"], the admission policy does not consider requests to apps/v1beta1 or extensions/v1beta1 API groups.

Equivalent: match a request if modifies a resource listed in rules, even via another API group or version. For example, if deployments can be modified via apps/v1, apps/v1beta1, and extensions/v1beta1, and "rules" only included apiGroups:["apps"], apiVersions:["v1"], resources: ["deployments"], the admission policy does consider requests made to apps/v1beta1 or extensions/v1beta1 API groups. The API server translates the request to a matched resource API if necessary.

Defaults to "Equivalent"

spec.matchResources.namespaceSelector (LabelSelector)

NamespaceSelector decides whether to run the admission control policy on an object based on whether the namespace for that object matches the selector. If the object itself is a namespace, the matching is performed on object.metadata.labels. If the object is another cluster scoped resource, it never skips the policy.

For example, to run the webhook on any objects whose namespace is not associated with "runlevel" of "0" or "1"; you will set the selector as follows: "namespaceSelector": { "matchExpressions": [ { "key": "runlevel", "operator": "NotIn", "values": [ "0", "1" ] } ] }

If instead you want to only run the policy on any objects whose namespace is associated with the "environment" of "prod" or "staging"; you will set the selector as follows: "namespaceSelector": { "matchExpressions": [ { "key": "environment", "operator": "In", "values": [ "prod", "staging" ] } ] }

See https://kubernetes.io/docs/concepts/overview/working-with-objects/labels/ for more examples of label selectors.

Default to the empty LabelSelector, which matches everything.

spec.matchResources.objectSelector (LabelSelector)

ObjectSelector decides whether to run the policy based on if the object has matching labels. objectSelector is evaluated against both the oldObject and newObject that would be sent to the policy's expression (CEL), and is considered to match if either object matches the selector. A null object (oldObject in the case of create, or newObject in the case of delete) or an object that cannot have labels (like a DeploymentRollback or a PodProxyOptions object) is not considered to match. Use the object selector only if the webhook is opt-in, because end users may skip the admission webhook by setting the labels. Default to the empty LabelSelector, which matches everything.

spec.matchResources.resourceRules ([]NamedRuleWithOperations)

Atomic: will be replaced during a merge

ResourceRules describes what operations on what resources/subresources the admission policy matches. The policy cares about an operation if it matches any Rule.

NamedRuleWithOperations is a tuple of Operations and Resources with ResourceNames.

spec.matchResources.resourceRules.apiGroups ([]string)

Atomic: will be replaced during a merge

APIGroups is the API groups the resources belong to. '' is all groups. If '' is present, the length of the slice must be one. Required.

spec.matchResources.resourceRules.apiVersions ([]string)

Atomic: will be replaced during a merge

APIVersions is the API versions the resources belong to. '' is all versions. If '' is present, the length of the slice must be one. Required.

spec.matchResources.resourceRules.operations ([]string)

Atomic: will be replaced during a merge

Operations is the operations the admission hook cares about - CREATE, UPDATE, DELETE, CONNECT or * for all of those operations and any future admission operations that are added. If '*' is present, the length of the slice must be one. Required.

spec.matchResources.resourceRules.resourceNames ([]string)

Atomic: will be replaced during a merge

ResourceNames is an optional white list of names that the rule applies to. An empty set means that everything is allowed.

spec.matchResources.resourceRules.resources ([]string)

Atomic: will be replaced during a merge

Resources is a list of resources this rule applies to.

For example: 'pods' means pods. 'pods/log' means the log subresource of pods. '' means all resources, but not subresources. 'pods/' means all subresources of pods. '/scale' means all scale subresources. '/*' means all resources and their subresources.

If wildcard is present, the validation rule will ensure resources do not overlap with each other.

Depending on the enclosing object, subresources might not be allowed. Required.

spec.matchResources.resourceRules.scope (string)

scope specifies the scope of this rule. Valid values are "Cluster", "Namespaced", and "" "Cluster" means that only cluster-scoped resources will match this rule. Namespace API objects are cluster-scoped. "Namespaced" means that only namespaced resources will match this rule. "" means that there are no scope restrictions. Subresources match the scope of their parent resource. Default is "*".

spec.paramRef (ParamRef)

paramRef specifies the parameter resource used to configure the admission control policy. It should point to a resource of the type specified in spec.ParamKind of the bound MutatingAdmissionPolicy. If the policy specifies a ParamKind and the resource referred to by ParamRef does not exist, this binding is considered mis-configured and the FailurePolicy of the MutatingAdmissionPolicy applied. If the policy does not specify a ParamKind then this field is ignored, and the rules are evaluated without a param.

ParamRef describes how to locate the params to be used as input to expressions of rules applied by a policy binding.

spec.paramRef.name (string)

name is the name of the resource being referenced.

name and selector are mutually exclusive properties. If one is set, the other must be unset.

spec.paramRef.namespace (string)

namespace is the namespace of the referenced resource. Allows limiting the search for params to a specific namespace. Applies to both name and selector fields.

A per-namespace parameter may be used by specifying a namespace-scoped paramKind in the policy and leaving this field empty.

If paramKind is cluster-scoped, this field MUST be unset. Setting this field results in a configuration error.

If paramKind is namespace-scoped, the namespace of the object being evaluated for admission will be used when this field is left unset. Take care that if this is left empty the binding must not match any cluster-scoped resources, which will result in an error.

spec.paramRef.parameterNotFoundAction (string)

parameterNotFoundAction controls the behavior of the binding when the resource exists, and name or selector is valid, but there are no parameters matched by the binding. If the value is set to Allow, then no matched parameters will be treated as successful validation by the binding. If set to Deny, then no matched parameters will be subject to the failurePolicy of the policy.

Allowed values are Allow or Deny Default to Deny

spec.paramRef.selector (LabelSelector)

selector can be used to match multiple param objects based on their labels. Supply selector: {} to match all resources of the ParamKind.

If multiple params are found, they are all evaluated with the policy expressions and the results are ANDed together.

One of name or selector must be set, but name and selector are mutually exclusive properties. If one is set, the other must be unset.

spec.policyName (string)

policyName references a MutatingAdmissionPolicy name which the MutatingAdmissionPolicyBinding binds to. If the referenced resource does not exist, this binding is considered invalid and will be ignored Required.





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