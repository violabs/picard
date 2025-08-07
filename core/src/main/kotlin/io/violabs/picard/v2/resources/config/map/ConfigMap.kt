package io.violabs.picard.v2.resources.config.map

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.TextData
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ConfigResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * ConfigMap holds configuration data for pods to consume.
 * apiVersion: v1
 *
 * import "k8s.io/api/core/v1"
 *
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/config-map-v1/
 */
@GeneratedDsl(withListGroup = true)
data class ConfigMap(
    @DefaultValue("KAPIVersion.V1", "io.violabs.picard.domain.k8sResources", "KAPIVersion")
    override val apiVersion: Version = KAPIVersion.V1,
    /**
     * Standard object's metadata.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * BinaryData contains the binary data. Each key must consist of alphanumeric
     * characters, '-', '_' or '.'. BinaryData can contain byte sequences that are not in
     * the UTF-8 range. The keys stored in BinaryData must not overlap with the ones in
     * the Data field, this is enforced during validation process. Using this field will
     * require 1.10+ apiserver and kubelet.
     */
    val binaryData: BinaryData? = null,
    /**
     * Data contains the configuration data. Each key must consist of alphanumeric
     * characters, '-', '_' or '.'. Values with non-UTF-8 byte sequences must use the
     * BinaryData field. The keys stored in Data must not overlap with the keys in the
     * BinaryData field, this is enforced during validation process.
     */
    val data: TextData? = null,
    /**
     * Immutable, if set to true, ensures that data stored in the ConfigMap cannot be
     * updated (only object metadata can be modified). If not set to true, the field can be
     * modified at any time. Defaulted to nil.
     */
    val immutable: Boolean? = null
) : ConfigResource<ConfigMap.Version, ObjectMeta> {
    interface Version : APIVersion
}