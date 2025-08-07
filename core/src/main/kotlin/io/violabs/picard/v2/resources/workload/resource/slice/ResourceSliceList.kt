package io.violabs.picard.v2.resources.workload.resource.slice

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ResourceSliceList(
    @DefaultValue(
        "KAPIVersion.ResourceV1Beta2",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta2,
    override val items: List<ResourceSlice>,
    override val metadata: ListMeta? = null
) : K8sListResource<ResourceSliceList.Version, ResourceSlice> {
    interface Version : APIVersion
}
