package io.violabs.picard.domain.k8sResources.extend.deviceClass

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class DeviceClassList(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    override val items: List<DeviceClass>,
    override val metadata: ListMeta? = null
) : K8sListResource<DeviceClassList.Version, DeviceClass> {
    interface Version : APIVersion
}
