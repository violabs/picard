package io.violabs.picard.domain.k8sResources.service.endpoints

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ServiceListResource

data class EndpointsList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<Endpoints>,
    override val metadata: ListMeta? = null
) : ServiceListResource<EndpointsList.Version, Endpoints> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        Endpoints,
        Endpoints.Builder,
        Endpoints.Group,
        EndpointsList
        >(Endpoints.Group()) {

        override fun build(): EndpointsList {
            return EndpointsList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}