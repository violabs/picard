package io.violabs.picard.domain.k8sResources.service

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ServiceList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<Service>,
    override val metadata: ListMeta? = null
) : K8sListResource<ServiceList.Version, Service> {
    interface Version : APIVersion
}