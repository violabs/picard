package io.violabs.picard.v2.resources.storage.csi.node

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.manifest.StorageListResource

@GeneratedDsl
data class CsiNodeList(
    @DefaultValue(
        "KAPIVersion.StorageV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val items: List<CsiNode>,
    override val metadata: ListMeta? = null
) : StorageListResource<CsiNodeList.Version, CsiNode> {
    interface Version : APIVersion

    override fun getKind(): String {
        return "CSINodeList"
    }
}
