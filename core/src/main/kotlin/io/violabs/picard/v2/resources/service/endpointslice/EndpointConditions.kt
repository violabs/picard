package io.violabs.picard.v2.resources.service.endpointslice

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * EndpointConditions represents the current condition of an endpoint.
 */
@GeneratedDsl
data class EndpointConditions(
    /**
     * ready indicates that this endpoint is ready to receive traffic, according to whatever system 
     * is managing the endpoint. A nil value should be interpreted as "true". In general, an endpoint 
     * should be marked ready if it is serving and not terminating, though this can be overridden in 
     * some cases, such as when the associated Service has set the publishNotReadyAddresses flag.
     */
    val ready: Boolean? = null,
    /**
     * serving indicates that this endpoint is able to receive traffic, according to whatever system 
     * is managing the endpoint. For endpoints backed by pods, the EndpointSlice controller will mark 
     * the endpoint as serving if the pod's Ready condition is True. A nil value should be interpreted 
     * as "true".
     */
    val serving: Boolean? = null,
    /**
     * terminating indicates that this endpoint is terminating. A nil value should be interpreted as "false".
     */
    val terminating: Boolean? = null
)