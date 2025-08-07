package io.violabs.picard.v2.resources.cluster.lease.candidate

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterListResource

/**
 * LeaseCandidateList is a list of LeaseCandidates.
 */
@GeneratedDsl
data class LeaseCandidateList(
    @DefaultValue(
        "KAPIVersion.CoordinationV1Alpha1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.CoordinationV1Alpha1,
    override val items: List<LeaseCandidate>,
    override val metadata: ListMeta? = null
) : ClusterListResource<LeaseCandidateList.Version, LeaseCandidate> {
    interface Version : APIVersion
}
