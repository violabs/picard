package io.violabs.picard.v2.resources.storage.volume.source

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Represents a Fibre Channel volume. Fibre Channel volumes can only be mounted as read/write once.
 * Fibre Channel volumes support ownership management and SELinux relabeling.
 */
@GeneratedDsl
data class FcVolumeSource(
    /**
     * fsType is the filesystem type to mount. Must be a filesystem type supported by the host
     * operating system. Ex. "ext4", "xfs", "ntfs". Implicitly inferred to be "ext4" if unspecified.
     */
    val fsType: String? = null,
    /**
     * lun is Optional: FC target lun number
     */
    val lun: Int? = null,
    /**
     * readOnly is Optional: Defaults to false (read/write). ReadOnly here will force the
     * ReadOnly setting in VolumeMounts.
     */
    val readOnly: Boolean? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * targetWWNs is Optional: FC target worldwide names (WWNs)
     */
    @JsonProperty("targetWWNs")
    val targetWwns: List<String>? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * wwids Optional: FC volume world wide identifiers (wwids) Either wwids or combination of
     * targetWWNs and lun must be set, but not both simultaneously.
     */
    val wwids: List<String>? = null
)