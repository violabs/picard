package io.violabs.picard.domain.k8sResources.cluster.namespace

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class NamespaceList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<Namespace>,
    override val metadata: ListMeta? = null
) : K8sListResource<NamespaceList.Version, Namespace> {
    interface Version : APIVersion
}
