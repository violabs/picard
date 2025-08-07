package io.violabs.picard.v2.resources.cluster.ipaddress

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterListResource

/**
 * IPAddressList is a list of IPAddresss.
 */
@GeneratedDsl
data class IpAddressListV2(
    @DefaultValue(
        "KAPIVersion.NetworkingV1Beta1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.NetworkingV1Beta1,
    override val items: List<IpAddressV2>,
    override val metadata: ListMeta? = null
) : ClusterListResource<IpAddressListV2.Version, IpAddressV2> {
    interface Version : APIVersion

    override fun getKind(): String {
        return "IPAddressList"
    }
}
