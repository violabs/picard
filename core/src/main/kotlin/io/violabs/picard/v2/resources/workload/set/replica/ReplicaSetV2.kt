package io.violabs.picard.v2.resources.workload.set.replica

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * ReplicaSet ensures that a specified number of pod replicas are running at any given time.
 *
 * apiVersion: apps/v1
 */
@GeneratedDsl
data class ReplicaSetV2(
    @DefaultValue(
        "KAPIVersion.AppsV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val metadata: ObjectMeta? = null,
    /**
     * Spec defines the specification of the desired behavior of the ReplicaSet.
     */
    val spec: ReplicaSetSpec? = null,
    /**
     * Status is the most recently observed status of the ReplicaSet. This data may be
     * out of date by some window of time. Populated by the system. Read-only.
     */
    val status: ReplicaSetStatus? = null
) : WorkloadResource<ReplicaSetV2.Version, ObjectMeta> {
    interface Version : APIVersion
}