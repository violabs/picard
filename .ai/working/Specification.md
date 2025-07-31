
# Working Specifications

## Prerequisites

- Access to the Picard codebase
- Gradle build environment set up
- Understanding of Kotlin and Kubernetes resource structure

## Implementation Workflow

Follow these steps in order:

1. **Create data classes** with proper annotations
2. **Update APIVersion declarations** to include V2 versions
3. **Add deprecation** to v1 classes
4. **Run clean build** to generate DSL builders
5. **Implement tests** using generated builders
6. **Run tests** to verify implementation

## Instruct

Build out objects with a specific format.
They should utilize `GeneratedDsl` and `DefaultValue` annotations. These items represent top
level Kubernetes resources, and should be structured to include the `apiVersion`, `metadata`, and
any specific fields relevant to the resource. Refer to the example below for guidance. When you are finished,
add items to .git.


### List Group Annotations

If there is a list property that needs DSL builder support, add `withListGroup = true` to the `GeneratedDsl` annotation **on the class level**:

```kotlin
// For the parent class that contains the list
@GeneratedDsl(withListGroup = true)
data class ParentResourceV2(...)

// For the child class that will be in the list
@GeneratedDsl(withListGroup = true) 
data class ChildCondition(...)
```

This generates both `conditions(vararg items: ChildCondition)` and `conditions { childCondition { ... } }` DSL methods.

### JsonProperty Usage

When field names need to preserve original JSON casing that differs from Kotlin conventions:

```kotlin
data class ComponentCondition(
    val status: String,
    val type: String,
    @JsonProperty("error")  // JSON field is "error", Kotlin property is "errorValue"
    val errorValue: String? = null,
    val message: String? = null
)
```

### APIVersion Integration

You will need to look at the package `io.violabs.picard.domain.k8sResources.APIVersion` to
add the V2 version as an extension of the existing version.

### Deprecation

Finally, update the v1 to have `@Deprecated("Use v2", ReplaceWith(<package for v2>))`

## Rules

- Time = LocalDateTime
- Quantity = io.violabs.picard.domain.k8sResources.Quantity
- Capitalized acronyms and initialisms should use camelCase (e.g., `APIService` -> `ApiService`, `CA` -> `Ca`)
  - Use `@JsonProperty` for properties to preserve the original casing if necessary.
  - Override `getKind(): String` to return the original casing for the kind if it extends a Resource

## Build Process

When you are finished creating the files:

1. **First**: Run `./gradlew clean build -x test` to generate DSL builders
2. **Then**: Implement tests using the generated builders
3. **Finally**: Run `./gradlew test` to verify everything works

If you do not fix the build after 3 times, you can ask for help.

## Troubleshooting

### Common Issues

1. **DSL builders not found during test compilation**
   - **Solution**: Run `./gradlew clean build -x test` first to generate the DSL classes

2. **Missing list group methods (e.g., `conditions { ... }`)**
   - **Solution**: Ensure both parent and child classes have `@GeneratedDsl(withListGroup = true)`

3. **JsonProperty compilation errors**
   - **Solution**: Import `com.fasterxml.jackson.annotation.JsonProperty`

4. **APIVersion compilation errors**
   - **Solution**: Verify the import was added and the Version interface extends APIVersion


## Documentation

Ingress
Ingress is a collection of rules that allow inbound connections to reach the endpoints defined by a backend.
apiVersion: networking.k8s.io/v1

import "k8s.io/api/networking/v1"

Ingress
Ingress is a collection of rules that allow inbound connections to reach the endpoints defined by a backend. An Ingress can be configured to give services externally-reachable urls, load balance traffic, terminate SSL, offer name based virtual hosting etc.

apiVersion: networking.k8s.io/v1

kind: Ingress

metadata (ObjectMeta)

Standard object's metadata. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata

spec (IngressSpec)

spec is the desired state of the Ingress. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

status (IngressStatus)

status is the current state of the Ingress. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

IngressSpec
IngressSpec describes the Ingress the user wishes to exist.

defaultBackend (IngressBackend)

defaultBackend is the backend that should handle requests that don't match any rule. If Rules are not specified, DefaultBackend must be specified. If DefaultBackend is not set, the handling of requests that do not match any of the rules will be up to the Ingress controller.

ingressClassName (string)

ingressClassName is the name of an IngressClass cluster resource. Ingress controller implementations use this field to know whether they should be serving this Ingress resource, by a transitive connection (controller -> IngressClass -> Ingress resource). Although the kubernetes.io/ingress.class annotation (simple constant name) was never formally defined, it was widely supported by Ingress controllers to create a direct binding between Ingress controller and Ingress resources. Newly created Ingress resources should prefer using the field. However, even though the annotation is officially deprecated, for backwards compatibility reasons, ingress controllers should still honor that annotation if present.

rules ([]IngressRule)

Atomic: will be replaced during a merge

rules is a list of host rules used to configure the Ingress. If unspecified, or no rule matches, all traffic is sent to the default backend.

IngressRule represents the rules mapping the paths under a specified host to the related backend services. Incoming requests are first evaluated for a host match, then routed to the backend associated with the matching IngressRuleValue.

rules.host (string)

host is the fully qualified domain name of a network host, as defined by RFC 3986. Note the following deviations from the "host" part of the URI as defined in RFC 3986: 1. IPs are not allowed. Currently an IngressRuleValue can only apply to the IP in the Spec of the parent Ingress. 2. The : delimiter is not respected because ports are not allowed. Currently the port of an Ingress is implicitly :80 for http and :443 for https. Both these may change in the future. Incoming requests are matched against the host before the IngressRuleValue. If the host is unspecified, the Ingress routes all traffic based on the specified IngressRuleValue.

host can be "precise" which is a domain name without the terminating dot of a network host (e.g. "foo.bar.com") or "wildcard", which is a domain name prefixed with a single wildcard label (e.g. ".foo.com"). The wildcard character '' must appear by itself as the first DNS label and matches only a single label. You cannot have a wildcard label by itself (e.g. Host == "*"). Requests will be matched against the Host field in the following way: 1. If host is precise, the request matches this rule if the http host header is equal to Host. 2. If host is a wildcard, then the request matches this rule if the http host header is to equal to the suffix (removing the first label) of the wildcard rule.

rules.http (HTTPIngressRuleValue)

HTTPIngressRuleValue is a list of http selectors pointing to backends. In the example: http:///? -> backend where where parts of the url correspond to RFC 3986, this resource will be used to match against everything after the last '/' and before the first '?' or '#'.

rules.http.paths ([]HTTPIngressPath), required

Atomic: will be replaced during a merge

paths is a collection of paths that map requests to backends.

HTTPIngressPath associates a path with a backend. Incoming urls matching the path are forwarded to the backend.

rules.http.paths.backend (IngressBackend), required

backend defines the referenced service endpoint to which the traffic will be forwarded to.

rules.http.paths.pathType (string), required

pathType determines the interpretation of the path matching. PathType can be one of the following values: * Exact: Matches the URL path exactly. * Prefix: Matches based on a URL path prefix split by '/'. Matching is done on a path element by element basis. A path element refers is the list of labels in the path split by the '/' separator. A request is a match for path p if every p is an element-wise prefix of p of the request path. Note that if the last element of the path is a substring of the last element in request path, it is not a match (e.g. /foo/bar matches /foo/bar/baz, but does not match /foo/barbaz).

ImplementationSpecific: Interpretation of the Path matching is up to the IngressClass. Implementations can treat this as a separate PathType or treat it identically to Prefix or Exact path types. Implementations are required to support all path types.
rules.http.paths.path (string)

path is matched against the path of an incoming request. Currently it can contain characters disallowed from the conventional "path" part of a URL as defined by RFC 3986. Paths must begin with a '/' and must be present when using PathType with value "Exact" or "Prefix".

tls ([]IngressTLS)

Atomic: will be replaced during a merge

tls represents the TLS configuration. Currently the Ingress only supports a single TLS port, 443. If multiple members of this list specify different hosts, they will be multiplexed on the same port according to the hostname specified through the SNI TLS extension, if the ingress controller fulfilling the ingress supports SNI.

IngressTLS describes the transport layer security associated with an ingress.

tls.hosts ([]string)

Atomic: will be replaced during a merge

hosts is a list of hosts included in the TLS certificate. The values in this list must match the name/s used in the tlsSecret. Defaults to the wildcard host setting for the loadbalancer controller fulfilling this Ingress, if left unspecified.

tls.secretName (string)

secretName is the name of the secret used to terminate TLS traffic on port 443. Field is left optional to allow TLS routing based on SNI hostname alone. If the SNI host in a listener conflicts with the "Host" header field used by an IngressRule, the SNI host is used for termination and value of the "Host" header is used for routing.

IngressBackend
IngressBackend describes all endpoints for a given service and port.

resource (TypedLocalObjectReference)

resource is an ObjectRef to another Kubernetes resource in the namespace of the Ingress object. If resource is specified, a service.Name and service.Port must not be specified. This is a mutually exclusive setting with "Service".

service (IngressServiceBackend)

service references a service as a backend. This is a mutually exclusive setting with "Resource".

IngressServiceBackend references a Kubernetes Service as a Backend.

service.name (string), required

name is the referenced service. The service must exist in the same namespace as the Ingress object.

service.port (ServiceBackendPort)

port of the referenced service. A port name or port number is required for a IngressServiceBackend.

ServiceBackendPort is the service port being referenced.

service.port.name (string)

name is the name of the port on the Service. This is a mutually exclusive setting with "Number".

service.port.number (int32)

number is the numerical port number (e.g. 80) on the Service. This is a mutually exclusive setting with "Name".

IngressStatus
IngressStatus describe the current state of the Ingress.

loadBalancer (IngressLoadBalancerStatus)

loadBalancer contains the current status of the load-balancer.

IngressLoadBalancerStatus represents the status of a load-balancer.

loadBalancer.ingress ([]IngressLoadBalancerIngress)

Atomic: will be replaced during a merge

ingress is a list containing ingress points for the load-balancer.

IngressLoadBalancerIngress represents the status of a load-balancer ingress point.

loadBalancer.ingress.hostname (string)

hostname is set for load-balancer ingress points that are DNS based.

loadBalancer.ingress.ip (string)

ip is set for load-balancer ingress points that are IP based.

loadBalancer.ingress.ports ([]IngressPortStatus)

Atomic: will be replaced during a merge

ports provides information about the ports exposed by this LoadBalancer.

IngressPortStatus represents the error condition of a service port

loadBalancer.ingress.ports.port (int32), required

port is the port number of the ingress port.

loadBalancer.ingress.ports.protocol (string), required

protocol is the protocol of the ingress port. The supported values are: "TCP", "UDP", "SCTP"

loadBalancer.ingress.ports.error (string)

error is to record the problem with the service port The format of the error shall comply with the following rules: - built-in error values shall be specified in this file and those shall use CamelCase names

cloud provider specific error values must have names that comply with the format foo.example.com/CamelCase.




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