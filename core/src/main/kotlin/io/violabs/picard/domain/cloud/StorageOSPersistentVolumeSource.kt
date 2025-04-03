package io.violabs.picard.domain.cloud

import io.violabs.picard.domain.ObjectReference

data class StorageOSPersistentVolumeSource(
    val volumeName: String? = null,
    val volumeNamespace: String? = null,
    val fsType: String? = null,
    val readOnly: Boolean? = null,
    val secretRef: ObjectReference? = null
)