package io.violabs.picard.domain.k8sResources.authentication

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ClusterTrustBundleList(
    override val apiVersion: Version = KAPIVersion.CertificatesV1,
    override val items: List<ClusterTrustBundle>,
    override val metadata: ListMeta? = null
) : K8sListResource<ClusterTrustBundleList.Version, ClusterTrustBundle> {
    interface Version : APIVersion
}
