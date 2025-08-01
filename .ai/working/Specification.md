
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
the documentation as comments. All classes should have their own file in the `v2` package.

Refer to the example below for guidance. When you are finished,
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

ResourceSlice v1beta2
ResourceSlice represents one or more resources in a pool of similar resources, managed by a common driver.
apiVersion: resource.k8s.io/v1beta2

import "k8s.io/api/resource/v1beta2"

ResourceSlice
ResourceSlice represents one or more resources in a pool of similar resources, managed by a common driver. A pool may span more than one ResourceSlice, and exactly how many ResourceSlices comprise a pool is determined by the driver.

At the moment, the only supported resources are devices with attributes and capacities. Each device in a given pool, regardless of how many ResourceSlices, must have a unique name. The ResourceSlice in which a device gets published may change over time. The unique identifier for a device is the tuple <driver name>, <pool name>, <device name>.

Whenever a driver needs to update a pool, it increments the pool.Spec.Pool.Generation number and updates all ResourceSlices with that new number and new resource definitions. A consumer must only use ResourceSlices with the highest generation number and ignore all others.

When allocating all resources in a pool matching certain criteria or when looking for the best solution among several different alternatives, a consumer should check the number of ResourceSlices in a pool (included in each ResourceSlice) to determine whether its view of a pool is complete and if not, should wait until the driver has completed updating the pool.

For resources that are not local to a node, the node name is not set. Instead, the driver may use a node selector to specify where the devices are available.

This is an alpha type and requires enabling the DynamicResourceAllocation feature gate.

apiVersion: resource.k8s.io/v1beta2

kind: ResourceSlice

metadata (ObjectMeta)

Standard object metadata

spec (ResourceSliceSpec), required

Contains the information published by the driver.

Changing the spec automatically increments the metadata.generation number.

ResourceSliceSpec
ResourceSliceSpec contains the information published by the driver in one ResourceSlice.

driver (string), required

Driver identifies the DRA driver providing the capacity information. A field selector can be used to list only ResourceSlice objects with a certain driver name.

Must be a DNS subdomain and should end with a DNS domain owned by the vendor of the driver. This field is immutable.

pool (ResourcePool), required

Pool describes the pool that this ResourceSlice belongs to.

ResourcePool describes the pool that ResourceSlices belong to.

pool.generation (int64), required

Generation tracks the change in a pool over time. Whenever a driver changes something about one or more of the resources in a pool, it must change the generation in all ResourceSlices which are part of that pool. Consumers of ResourceSlices should only consider resources from the pool with the highest generation number. The generation may be reset by drivers, which should be fine for consumers, assuming that all ResourceSlices in a pool are updated to match or deleted.

Combined with ResourceSliceCount, this mechanism enables consumers to detect pools which are comprised of multiple ResourceSlices and are in an incomplete state.

pool.name (string), required

Name is used to identify the pool. For node-local devices, this is often the node name, but this is not required.

It must not be longer than 253 characters and must consist of one or more DNS sub-domains separated by slashes. This field is immutable.

pool.resourceSliceCount (int64), required

ResourceSliceCount is the total number of ResourceSlices in the pool at this generation number. Must be greater than zero.

Consumers can use this to check whether they have seen all ResourceSlices belonging to the same pool.

allNodes (boolean)

AllNodes indicates that all nodes have access to the resources in the pool.

Exactly one of NodeName, NodeSelector, AllNodes, and PerDeviceNodeSelection must be set.

devices ([]Device)

Atomic: will be replaced during a merge

Devices lists some or all of the devices in this pool.

Must not have more than 128 entries.

Device represents one individual hardware instance that can be selected based on its attributes. Besides the name, exactly one field must be set.

devices.name (string), required

Name is unique identifier among all devices managed by the driver in the pool. It must be a DNS label.

devices.allNodes (boolean)

AllNodes indicates that all nodes have access to the device.

Must only be set if Spec.PerDeviceNodeSelection is set to true. At most one of NodeName, NodeSelector and AllNodes can be set.

devices.attributes (map[string]DeviceAttribute)

Attributes defines the set of attributes for this device. The name of each attribute must be unique in that set.

The maximum number of attributes and capacities combined is 32.

DeviceAttribute must have exactly one field set.

devices.attributes.bool (boolean)

BoolValue is a true/false value.

devices.attributes.int (int64)

IntValue is a number.

devices.attributes.string (string)

StringValue is a string. Must not be longer than 64 characters.

devices.attributes.version (string)

VersionValue is a semantic version according to semver.org spec 2.0.0. Must not be longer than 64 characters.

devices.capacity (map[string]DeviceCapacity)

Capacity defines the set of capacities for this device. The name of each capacity must be unique in that set.

The maximum number of attributes and capacities combined is 32.

DeviceCapacity describes a quantity associated with a device.

devices.capacity.value (Quantity), required

Value defines how much of a certain device capacity is available.

devices.consumesCounters ([]DeviceCounterConsumption)

Atomic: will be replaced during a merge

ConsumesCounters defines a list of references to sharedCounters and the set of counters that the device will consume from those counter sets.

There can only be a single entry per counterSet.

The total number of device counter consumption entries must be <= 32. In addition, the total number in the entire ResourceSlice must be <= 1024 (for example, 64 devices with 16 counters each).

DeviceCounterConsumption defines a set of counters that a device will consume from a CounterSet.

devices.consumesCounters.counterSet (string), required

CounterSet is the name of the set from which the counters defined will be consumed.

devices.consumesCounters.counters (map[string]Counter), required

Counters defines the counters that will be consumed by the device.

The maximum number counters in a device is 32. In addition, the maximum number of all counters in all devices is 1024 (for example, 64 devices with 16 counters each).

Counter describes a quantity associated with a device.

devices.consumesCounters.counters.value (Quantity), required

Value defines how much of a certain device counter is available.

devices.nodeName (string)

NodeName identifies the node where the device is available.

Must only be set if Spec.PerDeviceNodeSelection is set to true. At most one of NodeName, NodeSelector and AllNodes can be set.

devices.nodeSelector (NodeSelector)

NodeSelector defines the nodes where the device is available.

Must use exactly one term.

Must only be set if Spec.PerDeviceNodeSelection is set to true. At most one of NodeName, NodeSelector and AllNodes can be set.

A node selector represents the union of the results of one or more label queries over a set of nodes; that is, it represents the OR of the selectors represented by the node selector terms.

devices.nodeSelector.nodeSelectorTerms ([]NodeSelectorTerm), required

Atomic: will be replaced during a merge

Required. A list of node selector terms. The terms are ORed.

A null or empty node selector term matches no objects. The requirements of them are ANDed. The TopologySelectorTerm type implements a subset of the NodeSelectorTerm.

devices.nodeSelector.nodeSelectorTerms.matchExpressions ([]NodeSelectorRequirement)

Atomic: will be replaced during a merge

A list of node selector requirements by node's labels.

devices.nodeSelector.nodeSelectorTerms.matchFields ([]NodeSelectorRequirement)

Atomic: will be replaced during a merge

A list of node selector requirements by node's fields.

devices.taints ([]DeviceTaint)

Atomic: will be replaced during a merge

If specified, these are the driver-defined taints.

The maximum number of taints is 4.

This is an alpha field and requires enabling the DRADeviceTaints feature gate.

The device this taint is attached to has the "effect" on any claim which does not tolerate the taint and, through the claim, to pods using the claim.

devices.taints.effect (string), required

The effect of the taint on claims that do not tolerate the taint and through such claims on the pods using them. Valid effects are NoSchedule and NoExecute. PreferNoSchedule as used for nodes is not valid here.

devices.taints.key (string), required

The taint key to be applied to a device. Must be a label name.

devices.taints.timeAdded (Time)

TimeAdded represents the time at which the taint was added. Added automatically during create or update if not set.

Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON. Wrappers are provided for many of the factory methods that the time package offers.

devices.taints.value (string)

The taint value corresponding to the taint key. Must be a label value.

nodeName (string)

NodeName identifies the node which provides the resources in this pool. A field selector can be used to list only ResourceSlice objects belonging to a certain node.

This field can be used to limit access from nodes to ResourceSlices with the same node name. It also indicates to autoscalers that adding new nodes of the same type as some old node might also make new resources available.

Exactly one of NodeName, NodeSelector, AllNodes, and PerDeviceNodeSelection must be set. This field is immutable.

nodeSelector (NodeSelector)

NodeSelector defines which nodes have access to the resources in the pool, when that pool is not limited to a single node.

Must use exactly one term.

Exactly one of NodeName, NodeSelector, AllNodes, and PerDeviceNodeSelection must be set.

A node selector represents the union of the results of one or more label queries over a set of nodes; that is, it represents the OR of the selectors represented by the node selector terms.

nodeSelector.nodeSelectorTerms ([]NodeSelectorTerm), required

Atomic: will be replaced during a merge

Required. A list of node selector terms. The terms are ORed.

A null or empty node selector term matches no objects. The requirements of them are ANDed. The TopologySelectorTerm type implements a subset of the NodeSelectorTerm.

nodeSelector.nodeSelectorTerms.matchExpressions ([]NodeSelectorRequirement)

Atomic: will be replaced during a merge

A list of node selector requirements by node's labels.

nodeSelector.nodeSelectorTerms.matchFields ([]NodeSelectorRequirement)

Atomic: will be replaced during a merge

A list of node selector requirements by node's fields.

perDeviceNodeSelection (boolean)

PerDeviceNodeSelection defines whether the access from nodes to resources in the pool is set on the ResourceSlice level or on each device. If it is set to true, every device defined the ResourceSlice must specify this individually.

Exactly one of NodeName, NodeSelector, AllNodes, and PerDeviceNodeSelection must be set.

sharedCounters ([]CounterSet)

Atomic: will be replaced during a merge

SharedCounters defines a list of counter sets, each of which has a name and a list of counters available.

The names of the SharedCounters must be unique in the ResourceSlice.

The maximum number of counters in all sets is 32.

*CounterSet defines a named set of counters that are available to be used by devices defined in the ResourceSlice.

The counters are not allocatable by themselves, but can be referenced by devices. When a device is allocated, the portion of counters it uses will no longer be available for use by other devices.*

sharedCounters.counters (map[string]Counter), required

Counters defines the set of counters for this CounterSet The name of each counter must be unique in that set and must be a DNS label.

The maximum number of counters in all sets is 32.

Counter describes a quantity associated with a device.

sharedCounters.counters.value (Quantity), required

Value defines how much of a certain device counter is available.

sharedCounters.name (string), required

Name defines the name of the counter set. It must be a DNS label.







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