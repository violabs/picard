package io.violabs.picard.v2.resources.configstorage.volume

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/volume/
 */
@GeneratedDsl(withListGroup = true)
data class Volume(
    /**
     * Name of the volume. Must be a DNS_LABEL and unique within the pod.
     * More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names
     */
    val name: String,
    //region exposed persistent volumes
    /**
     * Reference to a PersistentVolumeClaim in the same namespace.
     */
    val persistentVolumeClaim: PersistentVolumeClaimVolumeSource? = null,
    //endregion

    //region projections
    /**
     * Represents a configMap that should populate this volume.
     */
    val configMap: ConfigMapVolumeSource? = null,
    /**
     * Represents a configMap that should populate this volume.
     */
    val secret: SecretVolumeSource? = null,
    /**
     * Represents downward API about the pod that should populate this volume
     */
    val downwardApi: DownwardApiVolumeSource? = null,
    val emptyDir: Any? = null
    //endregion
)