package io.violabs.picard.v2.resources.service

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ServiceResource
import io.violabs.picard.v2.common.ObjectMeta
import io.violabs.picard.v2.resources.service.status.ServiceStatus

/**
 * Service is a named abstraction of software service (for example, mysql) consisting of local port
 * (for example 3306) that the proxy listens on, and the selector that determines which pods will
 * answer requests sent through the proxy.
 *
 * apiVersion: v1
 * kind: Service
 */
@GeneratedDsl(withListGroup = true)
data class Service(
    @DefaultValue(
        "KAPIVersion.V1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMeta? = null,
    
    /**
     * Spec defines the behavior of a service.
     */
    val spec: ServiceSpec? = null,
    
    /**
     * Most recently observed status of the service. Populated by the system. Read-only.  
     */
    val status: ServiceStatus? = null
) : ServiceResource<Service.Version, ObjectMeta> {
    interface Version : APIVersion
}