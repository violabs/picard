package io.violabs.picard.domain.k8sResources.cluster.apiService

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterListResource

data class APIServiceList(
    override val apiVersion: Version = KAPIVersion.APIRegistrationV1,
    override val items: List<APIService>,
    override val metadata: ListMeta? = null
) : ClusterListResource<APIServiceList.Version, APIService> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        APIService,
        APIService.Builder,
        APIService.Group,
        APIServiceList
        >(APIService.Group()) {

        override fun build(): APIServiceList {
            return APIServiceList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
