package io.violabs.picard.v2.resources.workload.pod.container.status

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ResourceHealth represents the health of a resource.
 * It has the latest device health information. This is a part of KEP https://kep.k8s.io/4680.
 */
@GeneratedDsl
data class ResourceHealth(
    /**
     * ResourceID is the unique identifier of the resource.
     * See the ResourceID type for more information.
     */
    @JsonProperty("resourceID")
    val resourceId: String,
    /**
     * Health of the resource. can be one of:
     *
     * Healthy: operates as normal
     * Unhealthy: reported unhealthy. We consider this a temporary health
     *            issue since we do not have a mechanism today to distinguish
     *            temporary and permanent issues.
     * Unknown: The status cannot be determined. For example, Device Plugin got
     *          unregistered and hasn't been re-registered since.
     * In future we may want to introduce the PermanentlyUnhealthy Status.
     */
    val health: HealthStatus? = null
) {
    enum class HealthStatus {
        Healthy,
        Unhealthy,
        Unknown
    }
}