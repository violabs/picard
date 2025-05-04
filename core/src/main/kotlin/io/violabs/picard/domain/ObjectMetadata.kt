package io.violabs.picard.domain

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.label.Label
import io.violabs.picard.domain.label.LabelGroup

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
) {
    class Builder : DSLBuilder<ObjectMetadata> {
        var name: String? = null
        var generatedName: String? = null
        var namespace: String? = null
        private var labels: List<Label>? = null
        private var annotations: List<K8sAnnotation>? = null

        fun labels(scope: LabelGroup.() -> Unit) {
            labels = LabelGroup().apply(scope).labels()
        }

        fun annotations(scope: AnnotationGroup.() -> Unit) {
            annotations = AnnotationGroup().apply(scope).annotations()
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

    class AnnotationGroup {
        private var annotations: MutableList<K8sAnnotation> = mutableListOf()

        fun annotations(): MutableList<K8sAnnotation>? {
            return annotations
        }

        fun annotations(key: String, value: String) {
            annotations.add(K8sAnnotation(key, value))
        }
    }
}