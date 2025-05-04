package io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthenticationListResource

data class ClusterTrustBundleList(
    override val apiVersion: Version = KAPIVersion.CertificatesV1Alpha1,
    override val items: List<ClusterTrustBundle>,
    override val metadata: ListMeta? = null
) : AuthenticationListResource<ClusterTrustBundleList.Version, ClusterTrustBundle> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        ClusterTrustBundle,
        ClusterTrustBundle.Builder,
        ClusterTrustBundle.Group,
        ClusterTrustBundleList
        >(ClusterTrustBundle.Group()) {

        override fun build(): ClusterTrustBundleList {
            return ClusterTrustBundleList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
