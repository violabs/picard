package io.violabs.picard.v2.resources.service.ingress

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ServiceResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/service-resources/ingress-v1/
 *
 * Ingress is a collection of rules that allow inbound connections to reach the endpoints defined by a backend.
 * 
 * Ingress is a collection of rules that allow inbound connections to reach the endpoints defined by a backend. 
 * An Ingress can be configured to give services externally-reachable urls, load balance traffic, terminate SSL, 
 * offer name based virtual hosting etc.
 *
 * apiVersion: networking.k8s.io/v1
 *
 * import "k8s.io/api/networking/v1"
 */
@GeneratedDsl(withListGroup = true)
data class Ingress(
    @DefaultValue(
        "KAPIVersion.NetworkingV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    /**
     * Standard object's metadata. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * spec is the desired state of the Ingress. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val spec: IngressSpec? = null,
    /**
     * status is the current state of the Ingress. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val status: IngressStatus? = null
) : ServiceResource<Ingress.Version, ObjectMeta> {
    interface Version : APIVersion
}