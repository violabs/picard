package io.violabs.picard.domain.k8sResources.policy.resourceQuota

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ResourceQuotaList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<ResourceQuota>,
    override val metadata: ListMeta? = null
) : K8sListResource<ResourceQuotaList.Version, ResourceQuota> {
    interface Version : APIVersion
}
