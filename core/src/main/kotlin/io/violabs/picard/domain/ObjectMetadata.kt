package io.violabs.picard.domain

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/common-definitions/object-meta/#ObjectMeta
 * import "k8s.io/apimachinery/pkg/apis/meta/v1"
 * @property name required unless [generatedName] is provided
 * @property generatedName can only be set if [name] is not set
 * @property namespace defaults to 'default'
 * @property labels  may match selectors of replication controllers and services
 * @property annotations arbitrary metadata
 */
data class ObjectMetadata(
    val name: String? = null,
    val generatedName: String? = null,
    val namespace: String? = null,
    val labels: List<Label>? = null,
    val annotations: List<K8sAnnotation>? = null
)