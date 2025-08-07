package io.violabs.picard.v2.resources.cluster.lease

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * Lease defines a lease concept.
 *
 * apiVersion: coordination.k8s.io/v1
 *
 * import "k8s.io/api/coordination/v1"
 */
@GeneratedDsl(withListGroup = true)
data class LeaseV2(
    @DefaultValue(
        "KAPIVersion.CoordinationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.CoordinationV1,
    override val metadata: ObjectMeta? = null,
    /**
     * spec contains the specification of the Lease. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val spec: LeaseSpec? = null
) : ClusterResource<LeaseV2.Version, ObjectMeta> {
    interface Version : APIVersion
}