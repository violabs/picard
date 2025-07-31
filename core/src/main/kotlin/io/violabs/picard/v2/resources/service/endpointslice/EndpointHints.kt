package io.violabs.picard.v2.resources.service.endpointslice

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * EndpointHints provides hints describing how an endpoint should be consumed.
 */
@GeneratedDsl(withListGroup = true)
data class EndpointHints(
    /**
     * forNodes indicates the node(s) this endpoint should be consumed by when using topology aware 
     * routing. May contain a maximum of 8 entries. This is an Alpha feature and is only used when 
     * the PreferSameTrafficDistribution feature gate is enabled.
     */
    val forNodes: List<ForNode>? = null,
    /**
     * forZones indicates the zone(s) this endpoint should be consumed by when using topology aware 
     * routing. May contain a maximum of 8 entries.
     */
    val forZones: List<ForZone>? = null
)