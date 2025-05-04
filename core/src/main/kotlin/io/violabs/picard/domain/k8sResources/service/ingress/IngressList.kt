package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ServiceListResource

data class IngressList(
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val items: List<Ingress>,
    override val metadata: ListMeta? = null
) : ServiceListResource<IngressList.Version, Ingress> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        Ingress,
        Ingress.Builder,
        Ingress.Group,
        IngressList
        >(Ingress.Group()) {

        override fun build(): IngressList {
            return IngressList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}