package io.violabs.picard.domain.k8sResources.authentication.serviceAccount

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ServiceAccountList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<ServiceAccount>,
    override val metadata: ListMeta? = null
) : K8sListResource<ServiceAccountList.Version, ServiceAccount> {
    interface Version : APIVersion
}
