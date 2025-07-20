package io.violabs.picard.v2.resources.configstorage.volume.projection

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.configstorage.volume.KeyToPath

/**
 * Information about the secret data to project
 *
 * *Adapts a secret into a projected volume.
 *
 * The contents of the target Secret's Data field will be presented in a projected
 * volume as files using the keys in the Data field as the file names. Note that this
 * is identical to a secret volume source without the default mode.*
 */
@GeneratedDsl
data class SecretProjection(
    /**
     * Name of the referent. This field is effectively required, but due to
     * backwards compatibility is allowed to be empty. Instances of this type
     * with an empty value here are almost certainly wrong.
     * More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names
     */
    val name: String? = null,
    /**
     * optional field specify whether the Secret or its key must be defined
     */
    val optional: Boolean? = null,
    /**
     * projected.sources.secret.items ([]KeyToPath)
     *
     * Atomic: will be replaced during a merge
     *
     * items if unspecified, each key-value pair in the Data field of
     * the referenced Secret will be projected into the volume as a file
     * whose name is the key and content is the value. If specified, the
     * listed keys will be projected into the specified paths, and unlisted
     * keys will not be present. If a key is specified which is not present in
     * the Secret, the volume setup will error unless it is marked optional. Paths
     * must be relative and may not contain the '..' path or start with '..'.
     */
    val items: List<KeyToPath>? = null
)