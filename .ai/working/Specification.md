
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

StatefulSet
StatefulSet represents a set of pods with consistent identities.
apiVersion: apps/v1

import "k8s.io/api/apps/v1"

StatefulSet
StatefulSet represents a set of pods with consistent identities. Identities are defined as:

Network: A single stable DNS and hostname.
Storage: As many VolumeClaims as requested.
The StatefulSet guarantees that a given network identity will always map to the same storage identity.

apiVersion: apps/v1

kind: StatefulSet

metadata (ObjectMeta)

Standard object's metadata. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata

spec (StatefulSetSpec)

Spec defines the desired identities of pods in this set.

status (StatefulSetStatus)

Status is the current status of Pods in this StatefulSet. This data may be out of date by some window of time.

StatefulSetSpec
A StatefulSetSpec is the specification of a StatefulSet.

serviceName (string)

serviceName is the name of the service that governs this StatefulSet. This service must exist before the StatefulSet, and is responsible for the network identity of the set. Pods get DNS/hostnames that follow the pattern: pod-specific-string.serviceName.default.svc.cluster.local where "pod-specific-string" is managed by the StatefulSet controller.

selector (LabelSelector), required

selector is a label query over pods that should match the replica count. It must match the pod template's labels. More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/labels/#label-selectors

template (PodTemplateSpec), required

template is the object that describes the pod that will be created if insufficient replicas are detected. Each pod stamped out by the StatefulSet will fulfill this Template, but have a unique identity from the rest of the StatefulSet. Each pod will be named with the format <statefulsetname>-<podindex>. For example, a pod in a StatefulSet named "web" with index number "3" would be named "web-3". The only allowed template.spec.restartPolicy value is "Always".

replicas (int32)

replicas is the desired number of replicas of the given Template. These are replicas in the sense that they are instantiations of the same Template, but individual replicas also have a consistent identity. If unspecified, defaults to 1.

updateStrategy (StatefulSetUpdateStrategy)

updateStrategy indicates the StatefulSetUpdateStrategy that will be employed to update Pods in the StatefulSet when a revision is made to Template.

StatefulSetUpdateStrategy indicates the strategy that the StatefulSet controller will use to perform updates. It includes any additional parameters necessary to perform the update for the indicated strategy.

updateStrategy.type (string)

Type indicates the type of the StatefulSetUpdateStrategy. Default is RollingUpdate.

updateStrategy.rollingUpdate (RollingUpdateStatefulSetStrategy)

RollingUpdate is used to communicate parameters when Type is RollingUpdateStatefulSetStrategyType.

RollingUpdateStatefulSetStrategy is used to communicate parameter for RollingUpdateStatefulSetStrategyType.

updateStrategy.rollingUpdate.maxUnavailable (IntOrString)

The maximum number of pods that can be unavailable during the update. Value can be an absolute number (ex: 5) or a percentage of desired pods (ex: 10%). Absolute number is calculated from percentage by rounding up. This can not be 0. Defaults to 1. This field is alpha-level and is only honored by servers that enable the MaxUnavailableStatefulSet feature. The field applies to all pods in the range 0 to Replicas-1. That means if there is any unavailable pod in the range 0 to Replicas-1, it will be counted towards MaxUnavailable.

IntOrString is a type that can hold an int32 or a string. When used in JSON or YAML marshalling and unmarshalling, it produces or consumes the inner type. This allows you to have, for example, a JSON field that can accept a name or number.

updateStrategy.rollingUpdate.partition (int32)

Partition indicates the ordinal at which the StatefulSet should be partitioned for updates. During a rolling update, all pods from ordinal Replicas-1 to Partition are updated. All pods from ordinal Partition-1 to 0 remain untouched. This is helpful in being able to do a canary based deployment. The default value is 0.

podManagementPolicy (string)

podManagementPolicy controls how pods are created during initial scale up, when replacing pods on nodes, or when scaling down. The default policy is OrderedReady, where pods are created in increasing order (pod-0, then pod-1, etc) and the controller will wait until each pod is ready before continuing. When scaling down, the pods are removed in the opposite order. The alternative policy is Parallel which will create pods in parallel to match the desired scale without waiting, and on scale down will delete all pods at once.

revisionHistoryLimit (int32)

revisionHistoryLimit is the maximum number of revisions that will be maintained in the StatefulSet's revision history. The revision history consists of all revisions not represented by a currently applied StatefulSetSpec version. The default value is 10.

volumeClaimTemplates ([]PersistentVolumeClaim)

Atomic: will be replaced during a merge

volumeClaimTemplates is a list of claims that pods are allowed to reference. The StatefulSet controller is responsible for mapping network identities to claims in a way that maintains the identity of a pod. Every claim in this list must have at least one matching (by name) volumeMount in one container in the template. A claim in this list takes precedence over any volumes in the template, with the same name.

minReadySeconds (int32)

Minimum number of seconds for which a newly created pod should be ready without any of its container crashing for it to be considered available. Defaults to 0 (pod will be considered available as soon as it is ready)

persistentVolumeClaimRetentionPolicy (StatefulSetPersistentVolumeClaimRetentionPolicy)

persistentVolumeClaimRetentionPolicy describes the lifecycle of persistent volume claims created from volumeClaimTemplates. By default, all persistent volume claims are created as needed and retained until manually deleted. This policy allows the lifecycle to be altered, for example by deleting persistent volume claims when their stateful set is deleted, or when their pod is scaled down.

StatefulSetPersistentVolumeClaimRetentionPolicy describes the policy used for PVCs created from the StatefulSet VolumeClaimTemplates.

persistentVolumeClaimRetentionPolicy.whenDeleted (string)

WhenDeleted specifies what happens to PVCs created from StatefulSet VolumeClaimTemplates when the StatefulSet is deleted. The default policy of Retain causes PVCs to not be affected by StatefulSet deletion. The Delete policy causes those PVCs to be deleted.

persistentVolumeClaimRetentionPolicy.whenScaled (string)

WhenScaled specifies what happens to PVCs created from StatefulSet VolumeClaimTemplates when the StatefulSet is scaled down. The default policy of Retain causes PVCs to not be affected by a scaledown. The Delete policy causes the associated PVCs for any excess pods above the replica count to be deleted.

ordinals (StatefulSetOrdinals)

ordinals controls the numbering of replica indices in a StatefulSet. The default ordinals behavior assigns a "0" index to the first replica and increments the index by one for each additional replica requested.

StatefulSetOrdinals describes the policy used for replica ordinal assignment in this StatefulSet.

ordinals.start (int32)

start is the number representing the first replica's index. It may be used to number replicas from an alternate index (eg: 1-indexed) over the default 0-indexed names, or to orchestrate progressive movement of replicas from one StatefulSet to another. If set, replica indices will be in the range: [.spec.ordinals.start, .spec.ordinals.start + .spec.replicas). If unset, defaults to 0. Replica indices will be in the range: [0, .spec.replicas).

StatefulSetStatus
StatefulSetStatus represents the current state of a StatefulSet.

replicas (int32), required

replicas is the number of Pods created by the StatefulSet controller.

readyReplicas (int32)

readyReplicas is the number of pods created for this StatefulSet with a Ready Condition.

currentReplicas (int32)

currentReplicas is the number of Pods created by the StatefulSet controller from the StatefulSet version indicated by currentRevision.

updatedReplicas (int32)

updatedReplicas is the number of Pods created by the StatefulSet controller from the StatefulSet version indicated by updateRevision.

availableReplicas (int32)

Total number of available pods (ready for at least minReadySeconds) targeted by this statefulset.

collisionCount (int32)

collisionCount is the count of hash collisions for the StatefulSet. The StatefulSet controller uses this field as a collision avoidance mechanism when it needs to create the name for the newest ControllerRevision.

conditions ([]StatefulSetCondition)

Patch strategy: merge on key type

Map: unique values on key type will be kept during a merge

Represents the latest available observations of a statefulset's current state.

StatefulSetCondition describes the state of a statefulset at a certain point.

conditions.status (string), required

Status of the condition, one of True, False, Unknown.

conditions.type (string), required

Type of statefulset condition.

conditions.lastTransitionTime (Time)

Last time the condition transitioned from one status to another.

Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON. Wrappers are provided for many of the factory methods that the time package offers.

conditions.message (string)

A human readable message indicating details about the transition.

conditions.reason (string)

The reason for the condition's last transition.

currentRevision (string)

currentRevision, if not empty, indicates the version of the StatefulSet used to generate Pods in the sequence [0,currentReplicas).

updateRevision (string)¡

updateRevision, if not empty, indicates the version of the StatefulSet used to generate Pods in the sequence [replicas-updatedReplicas,replicas)

observedGeneration (int64)

observedGeneration is the most recent generation observed for this StatefulSet. It corresponds to the StatefulSet's generation, which is updated on mutation by the API Server.






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