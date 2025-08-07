package io.violabs.picard.v2.resources.storage.persistent.volume.claim

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class PersistentVolumeClaimList(
    @DefaultValue(
        "KAPIVersion.V1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<PersistentVolumeClaim>,
    override val metadata: ListMeta? = null
) : K8sListResource<PersistentVolumeClaimList.Version, PersistentVolumeClaim> {
    interface Version : APIVersion
}
