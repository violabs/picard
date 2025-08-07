package io.violabs.picard.v2.resources.cluster.lease.candidate

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * LeaseCandidate defines a candidate for a Lease object. Candidates are created
 * such that coordinated leader election will pick the best leader from the list of candidates.
 *
 * apiVersion: coordination.k8s.io/v1beta1
 *
 * import "k8s.io/api/coordination/v1beta1"
 */
@GeneratedDsl(withListGroup = true)
data class LeaseCandidate(
    @DefaultValue(
        "KAPIVersion.CoordinationV1Beta1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.CoordinationV1Beta1,
    override val metadata: ObjectMeta? = null,
    /**
     * spec contains the specification of the Lease.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val spec: LeaseCandidateSpec
) : ClusterResource<LeaseCandidate.Version, ObjectMeta> {
    interface Version : APIVersion
}