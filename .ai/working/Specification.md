
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
any specific fields relevant to the resource. There is documentation below to reference. You must include
the documentation as comments. All classes should have their own file in the `v2` package. This will live under
the v2/resources directory. DO NOT add all the properties in the docs above the class.

Refer to the example below for guidance. When you are finished,
add items to .git.


### List Group Annotations

If there is a list property that needs DSL builder support, add `withListGroup = true` to the `GeneratedDsl` annotation **on the class level**:

```kotlin
// For the parent class that contains the list
@GeneratedDsl
data class ParentResource(
    val childConditions: List<ChildCondition>
)

// For the child class that will be in the list
@GeneratedDsl(withListGroup = true) 
data class ChildCondition(...)
```

This generates both `conditions(vararg items: ChildCondition)` and `conditions { childCondition { ... } }` DSL methods.

### Map Group Annotations

If there are any properties that contain a entity that has `@GeneratedDsl` that is a value in a map, you should use
`MapGroupTypes.SINGLE`:

```kotlin
@GeneratedDsl
data class ParentResource(
    val mappableChild: Map<String, MappableChild>
)

@GeneratedDsl(withMapGroup = GeneratedDsl.MapGroupTypes.SINGLE)
data class MappableChild(...)
```

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

ReplicaSet
ReplicaSet ensures that a specified number of pod replicas are running at any given time.
apiVersion: apps/v1

import "k8s.io/api/apps/v1"

ReplicaSet
ReplicaSet ensures that a specified number of pod replicas are running at any given time.

apiVersion: apps/v1

kind: ReplicaSet

metadata (ObjectMeta)

If the Labels of a ReplicaSet are empty, they are defaulted to be the same as the Pod(s) that the ReplicaSet manages. Standard object's metadata. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata

spec (ReplicaSetSpec)

Spec defines the specification of the desired behavior of the ReplicaSet. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

status (ReplicaSetStatus)

Status is the most recently observed status of the ReplicaSet. This data may be out of date by some window of time. Populated by the system. Read-only. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

ReplicaSetSpec
ReplicaSetSpec is the specification of a ReplicaSet.

selector (LabelSelector), required

Selector is a label query over pods that should match the replica count. Label keys and values that must match in order to be controlled by this replica set. It must match the pod template's labels. More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/labels/#label-selectors

template (PodTemplateSpec)

Template is the object that describes the pod that will be created if insufficient replicas are detected. More info: https://kubernetes.io/docs/concepts/workloads/controllers/replicaset/#pod-template

replicas (int32)

Replicas is the number of desired pods. This is a pointer to distinguish between explicit zero and unspecified. Defaults to 1. More info: https://kubernetes.io/docs/concepts/workloads/controllers/replicaset

minReadySeconds (int32)

Minimum number of seconds for which a newly created pod should be ready without any of its container crashing, for it to be considered available. Defaults to 0 (pod will be considered available as soon as it is ready)

ReplicaSetStatus
ReplicaSetStatus represents the current status of a ReplicaSet.

replicas (int32), required

Replicas is the most recently observed number of non-terminating pods. More info: https://kubernetes.io/docs/concepts/workloads/controllers/replicaset

availableReplicas (int32)

The number of available non-terminating pods (ready for at least minReadySeconds) for this replica set.

readyReplicas (int32)

The number of non-terminating pods targeted by this ReplicaSet with a Ready Condition.

terminatingReplicas (int32)

The number of terminating pods for this replica set. Terminating pods have a non-null .metadata.deletionTimestamp and have not yet reached the Failed or Succeeded .status.phase.

This is an alpha field. Enable DeploymentReplicaSetTerminatingReplicas to be able to use this field.

fullyLabeledReplicas (int32)

The number of non-terminating pods that have labels matching the labels of the pod template of the replicaset.

conditions ([]ReplicaSetCondition)

Patch strategy: merge on key type

Map: unique values on key type will be kept during a merge

Represents the latest available observations of a replica set's current state.

ReplicaSetCondition describes the state of a replica set at a certain point.

conditions.status (string), required

Status of the condition, one of True, False, Unknown.

conditions.type (string), required

Type of replica set condition.

conditions.lastTransitionTime (Time)

The last time the condition transitioned from one status to another.

Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON. Wrappers are provided for many of the factory methods that the time package offers.

conditions.message (string)

A human readable message indicating details about the transition.

conditions.reason (string)

The reason for the condition's last transition.

observedGeneration (int64)

ObservedGeneration reflects the generation of the most recently observed ReplicaSet.




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