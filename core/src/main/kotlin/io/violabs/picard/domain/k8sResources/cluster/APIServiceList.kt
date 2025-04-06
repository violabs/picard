package io.violabs.picard.domain.k8sResources.cluster

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class APIServiceList(
    override val apiVersion: Version = KAPIVersion.APIRegistrationV1,
    override val items: List<APIService>,
    override val metadata: ListMeta? = null
) : K8sListResource<APIServiceList.Version, APIService> {
    interface Version : APIVersion
}
