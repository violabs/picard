package io.violabs.picard.v2.resources.authentication.cluster

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.manifest.AuthenticationListResource

@GeneratedDsl
data class ClusterTrustBundleList(
    @DefaultValue(
        "KAPIVersion.CertificatesV1Beta1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.CertificatesV1Beta1,
    override val items: List<ClusterTrustBundle>,
    override val metadata: ListMeta? = null
) : AuthenticationListResource<ClusterTrustBundleList.Version, ClusterTrustBundle> {
    interface Version : APIVersion
}
