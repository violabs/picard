package io.violabs.picard.v2.resources.workload.controller.replication

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * ReplicationController represents the configuration of a replication controller.
 *
 * apiVersion: v1
 */
@GeneratedDsl(withListGroup = true)
data class ReplicationController(
    @DefaultValue(
        "KAPIVersion.V1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMeta? = null,
    /**
     * Spec defines the specification of the desired behavior of the replication controller.
     */
    val spec: ReplicationControllerSpec? = null,
    /**
     * Status is the most recently observed status of the replication controller.
     * This data may be out of date by some window of time. Populated by the system. Read-only.
     */
    val status: ReplicationControllerStatus? = null
) : WorkloadResource<ReplicationController.Version, ObjectMeta> {
    interface Version : APIVersion
}