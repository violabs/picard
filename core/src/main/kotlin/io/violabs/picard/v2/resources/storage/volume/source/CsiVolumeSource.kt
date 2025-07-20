package io.violabs.picard.v2.resources.configstorage.volume.source

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.LocalObjectReference

/**
 * (Container Storage Interface) represents ephemeral storage that
 * is handled by certain external CSI drivers.
 */
@GeneratedDsl
data class CsiVolumeSource(
    val driver: String,
    val fsType: String? = null,
    val nodePublishSecretRef: LocalObjectReference? = null,
    val readOnly: Boolean? = null,
    val volumeAttributes: Map<String, String>? = null
)