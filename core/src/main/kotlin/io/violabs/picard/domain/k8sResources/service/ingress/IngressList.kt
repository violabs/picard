package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ServiceListResource

@Deprecated("Use v2", ReplaceWith("io.violabs.picard.v2.resources.service.ingress.IngressListV2"))
data class IngressList(
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val items: List<Ingress>,
    override val metadata: ListMeta? = null
) : ServiceListResource<IngressList.Version, Ingress> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
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