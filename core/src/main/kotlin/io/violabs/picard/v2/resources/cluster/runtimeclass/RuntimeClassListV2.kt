package io.violabs.picard.v2.resources.cluster.runtimeclass

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterListResource

/**
 * RuntimeClassList is a list of RuntimeClasss.
 */
@GeneratedDsl
data class RuntimeClassListV2(
    @DefaultValue(
        "KAPIVersion.NodeV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.NodeV1,
    override val items: List<RuntimeClassV2>,
    override val metadata: ListMeta? = null
) : ClusterListResource<RuntimeClassListV2.Version, RuntimeClassV2> {
    interface Version : APIVersion
}
