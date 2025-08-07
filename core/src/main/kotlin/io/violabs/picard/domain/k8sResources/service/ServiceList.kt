package io.violabs.picard.domain.k8sResources.service

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ServiceListResource

@Deprecated("Use v2", ReplaceWith("io.violabs.picard.v2.resources.service.ServiceListV2"))
data class ServiceList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<Service>,
    override val metadata: ListMeta? = null
) : ServiceListResource<ServiceList.Version, Service> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
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