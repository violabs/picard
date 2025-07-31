package io.violabs.picard.v2.resources.cluster.ipaddress

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * IPAddressSpec describe the attributes in an IP Address.
 */
@GeneratedDsl
data class IPAddressSpec(
    /**
     * ParentRef references the resource that an IPAddress is attached to. An IPAddress must reference a parent object.
     */
    val parentRef: ParentReference
)