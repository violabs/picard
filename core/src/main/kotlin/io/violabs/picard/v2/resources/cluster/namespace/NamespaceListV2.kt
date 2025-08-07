package io.violabs.picard.v2.resources.cluster.namespace

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterListResource

/**
 * NamespaceList is a list of Namespaces.
 */
@GeneratedDsl
data class NamespaceListV2(
    @DefaultValue(
        "KAPIVersion.V1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<NamespaceV2>,
    override val metadata: ListMeta? = null
) : ClusterListResource<NamespaceListV2.Version, NamespaceV2> {
    interface Version : APIVersion
}
