package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class IngressList(
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val items: List<Ingress>,
    override val metadata: ListMeta? = null
) : K8sListResource<IngressList.Version, Ingress> {
    interface Version : APIVersion
}