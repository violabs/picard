package io.violabs.picard.v2.resources.service.endpointslice

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ForZone provides information about which zones should consume this endpoint.
 */
@GeneratedDsl(withListGroup = true)
data class ForZone(
    /**
     * name represents the name of the zone.
     */
    val name: String
)