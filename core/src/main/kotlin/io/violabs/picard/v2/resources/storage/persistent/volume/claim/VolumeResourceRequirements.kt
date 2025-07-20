package io.violabs.picard.v2.resources.storage.persistent.volume.claim

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.Quantity

/**
 * Describes the storage resource requirements for a volume.
 */
@GeneratedDsl
data class VolumeResourceRequirements(
    /**
     * Limits describes the maximum amount of compute resources allowed.
     * More info: https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/
     */
    val limits: Map<String, Quantity>? = null,
    /**
     * Requests describes the minimum amount of compute resources required. If Requests is omitted for a
     * container, it defaults to Limits if that is explicitly specified, otherwise to an
     * implementation-defined value. Requests cannot exceed Limits.
     * More info: https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/
     */
    val requests: Map<String, Quantity>? = null
)