package io.violabs.picard.v2.resources.policy.network

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * NetworkPolicy describes what network traffic is allowed for a set of Pods.
 * apiVersion: networking.k8s.io/v1
 *
 * import "k8s.io/api/networking/v1"
 *
 * https://kubernetes.io/docs/reference/kubernetes-api/policy-resources/network-policy-v1/
 */
@GeneratedDsl(withListGroup = true)
data class NetworkPolicy(
    @DefaultValue(
        "KAPIVersion.NetworkingV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    /**
     * Standard object's metadata.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * spec represents the specification of the desired behavior for this NetworkPolicy.
     */
    val spec: NetworkPolicySpec? = null
) : PolicyResource<NetworkPolicy.Version, ObjectMeta> {
    interface Version : APIVersion
}