
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

PriorityLevelConfiguration
PriorityLevelConfiguration represents the configuration of a priority level.
apiVersion: flowcontrol.apiserver.k8s.io/v1

import "k8s.io/api/flowcontrol/v1"

PriorityLevelConfiguration
PriorityLevelConfiguration represents the configuration of a priority level.

apiVersion: flowcontrol.apiserver.k8s.io/v1

kind: PriorityLevelConfiguration

metadata (ObjectMeta)

metadata is the standard object's metadata. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata

spec (PriorityLevelConfigurationSpec)

spec is the specification of the desired behavior of a "request-priority". More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

status (PriorityLevelConfigurationStatus)

status is the current status of a "request-priority". More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

PriorityLevelConfigurationSpec
PriorityLevelConfigurationSpec specifies the configuration of a priority level.

exempt (ExemptPriorityLevelConfiguration)

exempt specifies how requests are handled for an exempt priority level. This field MUST be empty if type is "Limited". This field MAY be non-empty if type is "Exempt". If empty and type is "Exempt" then the default values for ExemptPriorityLevelConfiguration apply.

ExemptPriorityLevelConfiguration describes the configurable aspects of the handling of exempt requests. In the mandatory exempt configuration object the values in the fields here can be modified by authorized users, unlike the rest of the spec.

exempt.lendablePercent (int32)

lendablePercent prescribes the fraction of the level's NominalCL that can be borrowed by other priority levels. This value of this field must be between 0 and 100, inclusive, and it defaults to 0. The number of seats that other levels can borrow from this level, known as this level's LendableConcurrencyLimit (LendableCL), is defined as follows.

LendableCL(i) = round( NominalCL(i) * lendablePercent(i)/100.0 )

exempt.nominalConcurrencyShares (int32)

nominalConcurrencyShares (NCS) contributes to the computation of the NominalConcurrencyLimit (NominalCL) of this level. This is the number of execution seats nominally reserved for this priority level. This DOES NOT limit the dispatching from this priority level but affects the other priority levels through the borrowing mechanism. The server's concurrency limit (ServerCL) is divided among all the priority levels in proportion to their NCS values:

NominalCL(i) = ceil( ServerCL * NCS(i) / sum_ncs ) sum_ncs = sum[priority level k] NCS(k)

Bigger numbers mean a larger nominal concurrency limit, at the expense of every other priority level. This field has a default value of zero.

limited (LimitedPriorityLevelConfiguration)

limited specifies how requests are handled for a Limited priority level. This field must be non-empty if and only if type is "Limited".

*LimitedPriorityLevelConfiguration specifies how to handle requests that are subject to limits. It addresses two issues:

How are requests for this priority level limited?

What should be done with requests that exceed the limit?*

limited.borrowingLimitPercent (int32)

borrowingLimitPercent, if present, configures a limit on how many seats this priority level can borrow from other priority levels. The limit is known as this level's BorrowingConcurrencyLimit (BorrowingCL) and is a limit on the total number of seats that this level may borrow at any one time. This field holds the ratio of that limit to the level's nominal concurrency limit. When this field is non-nil, it must hold a non-negative integer and the limit is calculated as follows.

BorrowingCL(i) = round( NominalCL(i) * borrowingLimitPercent(i)/100.0 )

The value of this field can be more than 100, implying that this priority level can borrow a number of seats that is greater than its own nominal concurrency limit (NominalCL). When this field is left nil, the limit is effectively infinite.

limited.lendablePercent (int32)

lendablePercent prescribes the fraction of the level's NominalCL that can be borrowed by other priority levels. The value of this field must be between 0 and 100, inclusive, and it defaults to 0. The number of seats that other levels can borrow from this level, known as this level's LendableConcurrencyLimit (LendableCL), is defined as follows.

LendableCL(i) = round( NominalCL(i) * lendablePercent(i)/100.0 )

limited.limitResponse (LimitResponse)

limitResponse indicates what to do with requests that can not be executed right now

LimitResponse defines how to handle requests that can not be executed right now.

limited.limitResponse.type (string), required

type is "Queue" or "Reject". "Queue" means that requests that can not be executed upon arrival are held in a queue until they can be executed or a queuing limit is reached. "Reject" means that requests that can not be executed upon arrival are rejected. Required.

limited.limitResponse.queuing (QueuingConfiguration)

queuing holds the configuration parameters for queuing. This field may be non-empty only if type is "Queue".

QueuingConfiguration holds the configuration parameters for queuing

limited.limitResponse.queuing.handSize (int32)

handSize is a small positive number that configures the shuffle sharding of requests into queues. When enqueuing a request at this priority level the request's flow identifier (a string pair) is hashed and the hash value is used to shuffle the list of queues and deal a hand of the size specified here. The request is put into one of the shortest queues in that hand. handSize must be no larger than queues, and should be significantly smaller (so that a few heavy flows do not saturate most of the queues). See the user-facing documentation for more extensive guidance on setting this field. This field has a default value of 8.

limited.limitResponse.queuing.queueLengthLimit (int32)

queueLengthLimit is the maximum number of requests allowed to be waiting in a given queue of this priority level at a time; excess requests are rejected. This value must be positive. If not specified, it will be defaulted to 50.

limited.limitResponse.queuing.queues (int32)

queues is the number of queues for this priority level. The queues exist independently at each apiserver. The value must be positive. Setting it to 1 effectively precludes shufflesharding and thus makes the distinguisher method of associated flow schemas irrelevant. This field has a default value of 64.

limited.nominalConcurrencyShares (int32)

nominalConcurrencyShares (NCS) contributes to the computation of the NominalConcurrencyLimit (NominalCL) of this level. This is the number of execution seats available at this priority level. This is used both for requests dispatched from this priority level as well as requests dispatched from other priority levels borrowing seats from this level. The server's concurrency limit (ServerCL) is divided among the Limited priority levels in proportion to their NCS values:

NominalCL(i) = ceil( ServerCL * NCS(i) / sum_ncs ) sum_ncs = sum[priority level k] NCS(k)

Bigger numbers mean a larger nominal concurrency limit, at the expense of every other priority level.

If not specified, this field defaults to a value of 30.

Setting this field to zero supports the construction of a "jail" for this priority level that is used to hold some request(s)

type (string), required

type indicates whether this priority level is subject to limitation on request execution. A value of "Exempt" means that requests of this priority level are not subject to a limit (and thus are never queued) and do not detract from the capacity made available to other priority levels. A value of "Limited" means that (a) requests of this priority level are subject to limits and (b) some of the server's limited capacity is made available exclusively to this priority level. Required.

PriorityLevelConfigurationStatus
PriorityLevelConfigurationStatus represents the current state of a "request-priority".

conditions ([]PriorityLevelConfigurationCondition)

Patch strategy: merge on key type

Map: unique values on key type will be kept during a merge

conditions is the current state of "request-priority".

PriorityLevelConfigurationCondition defines the condition of priority level.

conditions.lastTransitionTime (Time)

lastTransitionTime is the last time the condition transitioned from one status to another.

Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON. Wrappers are provided for many of the factory methods that the time package offers.

conditions.message (string)

message is a human-readable message indicating details about last transition.

conditions.reason (string)

reason is a unique, one-word, CamelCase reason for the condition's last transition.

conditions.status (string)

status is the status of the condition. Can be True, False, Unknown. Required.

conditions.type (string)

type is the type of the condition. Required.






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