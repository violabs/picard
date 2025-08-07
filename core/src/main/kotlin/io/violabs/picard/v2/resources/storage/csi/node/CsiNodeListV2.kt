package io.violabs.picard.v2.resources.storage.csi.node

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class CsiNodeListV2(
    @DefaultValue(
        "KAPIVersion.StorageV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val items: List<CsiNodeV2>,
    override val metadata: ListMeta? = null
) : K8sListResource<CsiNodeListV2.Version, CsiNodeV2> {
    interface Version : APIVersion

    override fun getKind(): String {
        return "CSINodeList"
    }
}
