package io.violabs.picard.v2.resources.cluster.ipaddress

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * IPAddress represents a single IP of a single IP Family. The object is designed to be used by APIs that 
 * operate on IP addresses. The object is used by the Service core API for allocation of IP addresses. 
 * An IP address can be represented in different formats, to guarantee the uniqueness of the IP, the name 
 * of the object is the IP address in canonical format, four decimal digits separated by dots suppressing 
 * leading zeros for IPv4 and the representation defined by RFC 5952 for IPv6. 
 * Valid: 192.168.1.5 or 2001:db8::1 or 2001:db8:aaaa:bbbb:cccc:dddd:eeee:1 
 * Invalid: 10.01.2.3 or 2001:db8:0:0:0::1
 *
 * apiVersion: networking.k8s.io/v1
 *
 * import "k8s.io/api/networking/v1"
 */
@GeneratedDsl(withListGroup = true)
data class IpAddress(
    @DefaultValue(
        "KAPIVersion.NetworkingV1Beta1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.NetworkingV1Beta1,
    override val metadata: ObjectMeta? = null,
    /**
     * spec is the desired state of the IPAddress. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val spec: IPAddressSpec? = null
) : ClusterResource<IpAddress.Version, ObjectMeta> {
    interface Version : APIVersion

    override fun getKind(): String {
        return "IPAddress"
    }
}