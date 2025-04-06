package io.violabs.picard.domain.k8sResources.cluster

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class RuntimeClassList(
    override val apiVersion: Version = KAPIVersion.NodeV1,
    override val items: List<RuntimeClass>,
    override val metadata: ListMeta? = null
) : K8sListResource<RuntimeClassList.Version, RuntimeClass> {
    interface Version : APIVersion
}
