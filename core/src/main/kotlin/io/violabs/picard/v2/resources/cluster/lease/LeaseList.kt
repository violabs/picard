package io.violabs.picard.v2.resources.cluster.lease

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterListResource

/**
 * LeaseList is a list of Leases.
 */
@GeneratedDsl
data class LeaseList(
    @DefaultValue(
        "KAPIVersion.CoordinationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.CoordinationV1,
    override val items: List<Lease>,
    override val metadata: ListMeta? = null
) : ClusterListResource<LeaseList.Version, Lease> {
    interface Version : APIVersion
}
