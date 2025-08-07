package io.violabs.picard.v2.resources.storage.csi.storage.capacity

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class CsiStorageCapacityList(
    @DefaultValue(
        "KAPIVersion.StorageV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val items: List<CsiStorageCapacity>,
    override val metadata: ListMeta? = null
) : K8sListResource<CsiStorageCapacityList.Version, CsiStorageCapacity> {
    interface Version : APIVersion

    override fun getKind(): String {
        return "CSIStorageCapacityList"
    }
}
