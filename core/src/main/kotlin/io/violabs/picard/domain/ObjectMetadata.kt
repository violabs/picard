package io.violabs.picard.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.label.Label
import io.violabs.picard.serialization.ListAsMapSerializer
import io.violabs.picard.serialization.ListAsRetainedQuotesValueMapSerializer

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
    @JsonSerialize(using = ListAsMapSerializer::class)
    val labels: List<Label>? = null,
    @JsonSerialize(using = ListAsRetainedQuotesValueMapSerializer::class)
    val annotations: List<K8sAnnotation>? = null
) {
    class Builder : DslBuilder<ObjectMetadata> {
        var name: String? = null
        var generatedName: String? = null
        var namespace: String? = null
        private var labels: List<Label>? = null
        private var annotations: List<K8sAnnotation>? = null

        fun labels(scope: Label.Group.() -> Unit) {
            labels = Label.Group().apply(scope).labels()
        }

        fun annotations(scope: K8sAnnotation.Group.() -> Unit) {
            annotations = K8sAnnotation.Group().apply(scope).annotations()
        }

        override fun build(): ObjectMetadata {
            return ObjectMetadata(
                name,
                generatedName,
                namespace,
                labels,
                annotations
            )
        }
    }
}