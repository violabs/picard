package io.violabs.picard.v2.resources.storage.volume

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.ObjectFieldSelector
import io.violabs.picard.v2.common.ResourceFieldSelector

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/volume/#DownwardAPIVolumeFile
 *
 * Represents information to create the file containing the pod field
 */
@GeneratedDsl(withListGroup = true)
data class DownwardApiVolumeFile(
    /**
     * Required: Path is the relative path name of the file to be created. Must not be absolute
     * or contain the '..' path. Must be utf-8 encoded. The first item of the relative path
     * must not start with '..'
     */
    val path: String,
    /**
     * Selects a field of the pod: only annotations, labels, name, namespace and uid are supported.
     */
    val fieldRef: ObjectFieldSelector? = null,
    /**
     * Mode bits used to set permissions on this file, must be an octal value between 0000 and 0777
     * or a decimal value between 0 and 511. YAML accepts both octal and decimal values, JSON requires
     * decimal values for mode bits. If not specified, the volume defaultMode will be used. This might
     * be in conflict with other options that affect the file mode, like fsGroup, and the result can
     * be other mode bits set.
     */
    val mode: Int? = null,
    /**
     * Selects a resource of the container: only resources limits and requests
     * (limits.cpu, limits.memory, requests.cpu and requests.memory) are currently supported.
     */
    val resourceFieldRef: ResourceFieldSelector? = null
)