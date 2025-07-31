package io.violabs.picard.v2.resources.cluster.service.cidr

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * ServiceCIDR defines a range of IP addresses using CIDR format (e.g. 192.168.0.0/24 or 2001:db2::/64).
 * This range is used to allocate ClusterIPs to Service objects.
 *
 * apiVersion: networking.k8s.io/v1
 *
 * import "k8s.io/api/networking/v1"
 */
@GeneratedDsl
data class ServiceCidrV2(
    @DefaultValue(
        "KAPIVersion.NetworkingV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val metadata: ObjectMeta? = null,
    /**
     * spec is the desired state of the ServiceCIDR.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val spec: ServiceCidrSpec? = null,
    /**
     * status represents the current state of the ServiceCIDR.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val status: ServiceCidrStatus? = null
) : ClusterResource<ServiceCidrV2.Version, ObjectMeta> {
    interface Version : APIVersion

    override fun getKind(): String {
        return "ServiceCIDR"
    }
}