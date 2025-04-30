package io.violabs.picard.domain.k8sResources.storage.csi.csiDriver

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class CSIDriverList(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val items: List<CSIDriver>,
    override val metadata: ListMeta? = null
) : K8sListResource<CSIDriverList.Version, CSIDriver> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        CSIDriver,
        CSIDriver.Builder,
        CSIDriver.Group,
        CSIDriverList
        >(CSIDriver.Group()) {

        override fun build(): CSIDriverList {
            return CSIDriverList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}