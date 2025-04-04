package io.violabs.picard.domain

import io.violabs.picard.domain.k8sResources.ConfigMap
import io.violabs.picard.domain.k8sResources.PersistentVolumeClaim

data class Volume(
    val name: String,
    val emptyDir: String? = null
)

data class ExposedPersistentVolumes(
    val persistentVolumeClaim: PersistentVolumeClaim.VolumeSource
)

data class Projections(
    val configMap: ConfigMap.VolumeSource? = null,
    val secret: SecretVolumeSource? = null,
    val downwardAPI: DownwardAPIVolumeSource? = null,
)

data class KeyToPath(
    val key: String,
    val path: String,
    val mode: Int? = null
)