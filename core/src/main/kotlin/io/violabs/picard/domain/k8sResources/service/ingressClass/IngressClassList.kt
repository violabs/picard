package io.violabs.picard.domain.k8sResources.service.ingressClass

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ServiceListResource

data class IngressClassList(
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val items: List<IngressClass>,
    override val metadata: ListMeta? = null
) : ServiceListResource<IngressClassList.Version, IngressClass> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        IngressClass,
        IngressClass.Builder,
        IngressClass.Group,
        IngressClassList
        >(IngressClass.Group()) {

        override fun build(): IngressClassList {
            return IngressClassList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}