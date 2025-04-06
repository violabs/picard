package io.violabs.picard.domain.k8sResources.cluster

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class LeaseList(
    override val apiVersion: Version = KAPIVersion.CoordinationV1,
    override val items: List<Lease>,
    override val metadata: ListMeta? = null
) : K8sListResource<LeaseList.Version, Lease> {
    interface Version : APIVersion
}
