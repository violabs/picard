package io.violabs.picard.v2.resources.workload.deployment

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * Deployment enables declarative updates for Pods and ReplicaSets.
 *
 * apiVersion: apps/v1
 */
@GeneratedDsl(withListGroup = true)
data class DeploymentV2(
    @DefaultValue(
        "KAPIVersion.AppsV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val metadata: ObjectMeta? = null,
    /**
     * Specification of the desired behavior of the Deployment.
     */
    val spec: DeploymentSpec? = null,
    /**
     * Most recently observed status of the Deployment.
     */
    val status: DeploymentStatus? = null
) : WorkloadResource<DeploymentV2.Version, ObjectMeta> {
    interface Version : APIVersion
}