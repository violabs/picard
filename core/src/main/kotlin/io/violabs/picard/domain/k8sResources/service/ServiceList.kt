package io.violabs.picard.domain.k8sResources.service

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ServiceList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<Service>,
    override val metadata: ListMeta? = null
) : K8sListResource<ServiceList.Version, Service> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        Service,
        Service.Builder,
        Service.Group,
        ServiceList
        >(Service.Group()) {

        override fun build(): ServiceList {
            return ServiceList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}