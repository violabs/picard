package io.violabs.picard.v2.resources.authentication.cluster

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ClusterTrustBundleListV2(
    @DefaultValue(
        "KAPIVersion.CertificatesV1Beta1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.CertificatesV1Beta1,
    override val items: List<ClusterTrustBundleV2>,
    override val metadata: ListMeta? = null
) : K8sListResource<ClusterTrustBundleListV2.Version, ClusterTrustBundleV2> {
    interface Version : APIVersion
}
