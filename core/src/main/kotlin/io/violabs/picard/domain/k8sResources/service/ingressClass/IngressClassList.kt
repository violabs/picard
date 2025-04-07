package io.violabs.picard.domain.k8sResources.service.ingressClass

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class IngressClassList(
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val items: List<IngressClass>,
    override val metadata: ListMeta? = null
) : K8sListResource<IngressClassList.Version, IngressClass> {
    interface Version : APIVersion
}