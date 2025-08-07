package io.violabs.picard.v2.resources.extend.deviceclass

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ExtendListResource

/**
 * DeviceClassList is a list of DeviceClasss.
 */
@GeneratedDsl
data class DeviceClassListV2(
    @DefaultValue(
        "KAPIVersion.ResourceV1Beta1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    override val items: List<DeviceClassV2>,
    override val metadata: ListMeta? = null
) : ExtendListResource<DeviceClassListV2.Version, DeviceClassV2> {
    interface Version : APIVersion
}
