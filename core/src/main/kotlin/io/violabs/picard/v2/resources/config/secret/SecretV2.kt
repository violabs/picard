package io.violabs.picard.v2.resources.config.secret

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.TextData
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ConfigResource
import io.violabs.picard.v2.common.ObjectMeta

@GeneratedDsl
@JsonPropertyOrder("apiVersion", "kind", "metadata", "type", "data", "immutable")
data class SecretV2(
    @DefaultValue("KAPIVersion.V1", "io.violabs.picard.domain.k8sResources", "KAPIVersion")
    override val apiVersion: Version = KAPIVersion.V1,
    /**
     * Standard object's metadata.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * Data contains the secret data. Each key must consist of alphanumeric
     * characters, '-', '_' or '.'. The serialized form of the secret data is a
     * base64 encoded string, representing the arbitrary (possibly non-string)
     * data value here. Described in https://tools.ietf.org/html/rfc4648#section-4
     */
    val data: BinaryData? = null,
    /**
     * stringData allows specifying non-binary secret data in string form. It is
     * provided as a write-only input field for convenience. All keys and values
     * are merged into the data field on write, overwriting any existing values.
     * The stringData field is never output when reading from the API.
     */
    val stringData: TextData? = null,
    /**
     * Immutable, if set to true, ensures that data stored in the Secret cannot be
     * updated (only object metadata can be modified). If not set to true, the field
     * can be modified at any time. Defaulted to nil.
     */
    val immutable: Boolean? = null,
    /**
     * Used to facilitate programmatic handling of secret data.
     * More info: https://kubernetes.io/docs/concepts/configuration/secret/#secret-types
     */
    val type: SecretType? = null
) : ConfigResource<SecretV2.Version, ObjectMeta> {
    interface Version : APIVersion
}