package io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ClusterTrustBundleList(
    override val apiVersion: Version = KAPIVersion.CertificatesV1Alpha1,
    override val items: List<ClusterTrustBundle>,
    override val metadata: ListMeta? = null
) : K8sListResource<ClusterTrustBundleList.Version, ClusterTrustBundle> {
    interface Version : APIVersion

    class Builder : DSLBuilder<ClusterTrustBundleList> {
        private var items: List<ClusterTrustBundle>? = null
        private var metadata: ListMeta? = null

        fun items(scope: ClusterTrustBundle.Group.() -> Unit) {
            items = ClusterTrustBundle.Group().apply(scope).listItems()
        }

        fun metadata(scope: ListMeta.Builder.() -> Unit) {
            metadata = ListMeta.Builder().apply(scope).build()
        }

        override fun build(): ClusterTrustBundleList {
            return ClusterTrustBundleList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
