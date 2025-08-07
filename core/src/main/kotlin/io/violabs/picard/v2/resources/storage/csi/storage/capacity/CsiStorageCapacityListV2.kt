package io.violabs.picard.v2.resources.storage.csi.storage.capacity

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class CsiStorageCapacityListV2(
    @DefaultValue(
        "KAPIVersion.StorageV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val items: List<CsiStorageCapacityV2>,
    override val metadata: ListMeta? = null
) : K8sListResource<CsiStorageCapacityListV2.Version, CsiStorageCapacityV2> {
    interface Version : APIVersion

    override fun getKind(): String {
        return "CSIStorageCapacityList"
    }
}
