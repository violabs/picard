package io.violabs.picard.v2.resources.storage.csi.driver

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class CsiDriverList(
    @DefaultValue(
        "KAPIVersion.StorageV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val items: List<CsiDriver>,
    override val metadata: ListMeta? = null
) : K8sListResource<CsiDriverList.Version, CsiDriver> {
    interface Version : APIVersion

    override fun getKind(): String {
        return "CSIDriverList"
    }
}
