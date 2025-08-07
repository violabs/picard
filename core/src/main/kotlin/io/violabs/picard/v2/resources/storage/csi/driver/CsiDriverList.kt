package io.violabs.picard.v2.resources.storage.csi.driver

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.manifest.StorageListResource

@GeneratedDsl
data class CsiDriverList(
    @DefaultValue(
        "KAPIVersion.StorageV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val items: List<CsiDriver>,
    override val metadata: ListMeta? = null
) : StorageListResource<CsiDriverList.Version, CsiDriver> {
    interface Version : APIVersion

    override fun getKind(): String {
        return "CSIDriverList"
    }
}
