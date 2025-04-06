package io.violabs.picard.domain.k8sResources.service.endpoints

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class EndpointsList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<Endpoints>,
    override val metadata: ListMeta? = null
) : K8sListResource<EndpointsList.Version, Endpoints> {
    interface Version : APIVersion
}