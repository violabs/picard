package io.violabs.picard.v2.resources.service.endpoints

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ServiceResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * Endpoints is a collection of endpoints that implement the actual service.
 * 
 * Example:
 * Name: "mysvc", Subsets: [ { Addresses: [{"ip": "10.10.1.1"}, {"ip": "10.10.2.2"}], 
 * Ports: [{"name": "a", "port": 8675}, {"name": "b", "port": 309}] }, 
 * { Addresses: [{"ip": "10.10.3.3"}], Ports: [{"name": "a", "port": 93}, {"name": "b", "port": 76}] }, ]
 *
 * Endpoints is a legacy API and does not contain information about all Service features. 
 * Use discoveryv1.EndpointSlice for complete information about Service endpoints.
 *
 * Deprecated: This API is deprecated in v1.33+. Use discoveryv1.EndpointSlice.
 *
 * apiVersion: v1
 * kind: Endpoints
 */
@GeneratedDsl(withListGroup = true)
data class EndpointsV2(
    @DefaultValue(
        "KAPIVersion.V1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMeta? = null,
    
    /**
     * The set of all endpoints is the union of all subsets. Addresses are placed into subsets 
     * according to the IPs they share. A single address with multiple ports, some of which are 
     * ready and some of which are not (because they come from different containers) will result 
     * in the address being displayed in different subsets for the different ports. No address 
     * will appear in both Addresses and NotReadyAddresses in the same subset. Sets of addresses 
     * and ports that comprise a service.
     */
    val subsets: List<EndpointSubset>? = null
) : ServiceResource<EndpointsV2.Version, ObjectMeta> {
    interface Version : APIVersion
}