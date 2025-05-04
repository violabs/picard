package io.violabs.picard.domain.k8sResources.workload.resourceSlice

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class ResourceSliceList(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    override val items: List<ResourceSlice>,
    override val metadata: ListMeta? = null
) : K8sListResource<ResourceSliceList.Version, ResourceSlice> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        ResourceSlice,
        ResourceSlice.Builder,
        ResourceSlice.Group,
        ResourceSliceList>(ResourceSlice.Group()) {

        override fun build(): ResourceSliceList {
            return ResourceSliceList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}