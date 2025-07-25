package io.violabs.picard.v2.resources.storage.volume.source

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.storage.volume.KeyToPath

/**
 * Represents a secret that should populate
 * this volume. More info: https://kubernetes.io/docs/concepts/storage/volumes#secret
 *
 * *Adapts a Secret into a volume.
 *
 * The contents of the target Secret's Data field will be presented in a volume as files
 * using the keys in the Data field as the file names. Secret volumes support ownership
 * management and SELinux relabeling.*
 */
@GeneratedDsl
data class SecretVolumeSource(
    /**
     * The name of the secret in the pod's namespace to use.
     * More info: https://kubernetes.io/docs/concepts/storage/volumes#secret
     */
    val secretName: String? = null,
    /**
     * Specify whether the Secret or its keys must be defined
     */
    val optional: Boolean? = null,
    /**
     * Mode bits used to set permissions on created files by default. Must be an octal value between
     * 0000 and 0777 or a decimal value between 0 and 511. YAML accepts both octal and decimal values,
     * JSON requires decimal values for mode bits. Defaults to 0644. Directories within the path are
     * not affected by this setting. This might be in conflict with other options that affect the file mode,
     * like fsGroup, and the result can be other mode bits set.
     */
    val defaultMode: Int? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * If unspecified, each key-value pair in the Data field of the referenced Secret
     * will be projected into the volume as a file whose name is the key and content is the
     * value. If specified, the listed keys will be projected into the specified paths, and
     * unlisted keys will not be present. If a key is specified which is not present in the Secret,
     * the volume setup will error unless it is marked optional. Paths must be relative and may not
     * contain the '..' path or start with '..'.
     */
    val items: List<KeyToPath>? = null
)