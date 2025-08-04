package io.violabs.picard.v2.resources.workload.pod.container.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ResourceStatus represents the status of a single resource allocated to a Pod.
 */
@GeneratedDsl
data class ResourceStatus(
    /**
     * Name of the resource. Must be unique within the pod and in case of non-DRA resource,
     * match one of the resources from the pod spec.
     * For DRA resources, the value must be "claim:<claim_name>/<request>".
     * When this status is reported about a container, the "claim_name" and "request" must
     * match one of the claims of this container.
     */
    val name: String,
    /**
     * Map: unique values on key resourceID will be kept during a merge
     *
     * List of unique resources health.
     * Each element in the list contains an unique resource ID and its health.
     * At a minimum, for the lifetime of a Pod, resource ID must uniquely identify
     * the resource allocated to the Pod on the Node. If other Pod on the same
     * Node reports the status with the same resource ID, it must be the same resource they share.
     * See ResourceID type definition for a specific format it has in various use cases.
     */
    val resources: List<ResourceHealth>? = null
)