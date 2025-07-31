package io.violabs.picard.v2.resources.service

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.v2.resources.service.config.SessionAffinityConfig

@GeneratedDsl(withListGroup = true)
data class ServiceSpec(
    /**
     * Route service traffic to pods with label keys and values matching this selector.
     * If empty or not present, the service is assumed to have an external process managing its endpoints.
     */
    val selector: Map<String, String>? = null,

    /**
     * The list of ports that are exposed by this service.
     */
    val ports: List<ServicePort>? = null,

    /**
     * Type determines how the Service is exposed. Defaults to ClusterIP.
     * Valid options are ExternalName, ClusterIP, NodePort, and LoadBalancer.
     */
    val type: String? = null,

    /**
     * IPFamilies is a list of IP families (e.g. IPv4, IPv6) assigned to this service.
     */
    @JsonProperty("ipFamilies")
    val ipFamilies: List<String>? = null,

    /**
     * IPFamilyPolicy represents the dual-stack-ness requested or required by this Service.
     */
    @JsonProperty("ipFamilyPolicy")
    val ipFamilyPolicy: String? = null,

    /**
     * clusterIP is the IP address of the service and is usually assigned randomly.
     */
    @JsonProperty("clusterIP")
    val clusterIp: String? = null,

    /**
     * ClusterIPs is a list of IP addresses assigned to this service.
     */
    @JsonProperty("clusterIPs")
    val clusterIps: List<String>? = null,

    /**
     * externalIPs is a list of IP addresses for which nodes in the cluster will also accept traffic.
     */
    @JsonProperty("externalIPs")
    val externalIps: List<String>? = null,

    /**
     * Supports "ClientIP" and "None". Used to maintain session affinity.
     */
    val sessionAffinity: String? = null,

    /**
     * Only applies to Service Type: LoadBalancer.
     */
    @JsonProperty("loadBalancerIP")
    val loadBalancerIp: String? = null,

    /**
     * If specified and supported by the platform, this will restrict traffic through the cloud-provider load-balancer.
     */
    val loadBalancerSourceRanges: List<String>? = null,

    /**
     * loadBalancerClass is the class of the load balancer implementation this Service belongs to.
     */
    val loadBalancerClass: String? = null,

    /**
     * externalName is the external reference that discovery mechanisms will return as an alias for this service.
     */
    val externalName: String? = null,

    /**
     * externalTrafficPolicy describes how nodes distribute service traffic they receive on externally-facing addresses.
     */
    val externalTrafficPolicy: String? = null,

    /**
     * InternalTrafficPolicy describes how nodes distribute service traffic they receive on the ClusterIP.
     */
    val internalTrafficPolicy: String? = null,

    /**
     * healthCheckNodePort specifies the healthcheck nodePort for the service.
     */
    val healthCheckNodePort: Int? = null,

    /**
     * publishNotReadyAddresses indicates that any agent which deals with endpoints for this Service
     * should disregard any indications of ready/not-ready.
     */
    val publishNotReadyAddresses: Boolean? = null,

    /**
     * sessionAffinityConfig contains the configurations of session affinity.
     */
    val sessionAffinityConfig: SessionAffinityConfig? = null,

    /**
     * allocateLoadBalancerNodePorts defines if NodePorts will be automatically allocated for services with type LoadBalancer.
     */
    val allocateLoadBalancerNodePorts: Boolean? = null,

    /**
     * TrafficDistribution offers a way to express preferences for how traffic is distributed to Service endpoints.
     */
    val trafficDistribution: String? = null
) : BaseSpec