package io.violabs.picard.v2.resources.workload.pod

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * Pod is a collection of containers that can run on a host.
 * apiVersion: v1
 *
 * import "k8s.io/api/core/v1"
 *
 * Pod
 * Pod is a collection of containers that can run on a host.
 * This resource is created by clients and scheduled onto hosts.
 *
 * apiVersion: v1
 *
 * kind: Pod
 */
@GeneratedDsl(withListGroup = true)
data class PodV2(
    @DefaultValue(
        "KAPIVersion.V1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.V1,
    /**
     * Standard object's metadata.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * Specification of the desired behavior of the pod.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val spec: PodSpec? = null,
    /**
     * Most recently observed status of the pod. This data may not be up to date.
     * Populated by the system. Read-only.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val status: PodStatus? = null
) : WorkloadResource<PodV2.Version, ObjectMeta> {
    interface Version : APIVersion
}